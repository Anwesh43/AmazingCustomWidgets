package com.anwesome.ui.bulletedlist;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.hardware.display.DisplayManager;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.*;

/**
 * Created by anweshmishra on 06/02/17.
 */
public class BulletedList {
    private String currentItem = "Select";
    private Activity activity;
    private int w=300,h=300;
    private int color = Color.BLACK;
    private List<String> items = new ArrayList<>();
    public BulletedList(Activity activity) {
        this.activity = activity;
        initDimensions();
    }
    public void initDimensions() {
        DisplayManager displayManager = (DisplayManager)activity.getSystemService(Context.DISPLAY_SERVICE);
        Display display = displayManager.getDisplay(0);
        if(display!=null) {
            Point size = new Point();
            display.getRealSize(size);
            w = size.x;
            h = size.y;
        }
    }
    public void show(int x,int y) {
        BulletedListView bulletedListView = new BulletedListView(activity);
        bulletedListView.setX(x);
        bulletedListView.setY(y);
        activity.addContentView(bulletedListView,new ViewGroup.LayoutParams(w/2,h/3));
    }
    public void addItem(String item) {
        this.items.add(item);
    }
    public void setItems(String items[]) {
        for(String item:items) {
            this.items.add(item);
        }
    }
    public void setItems(List<String> items) {
        this.items = items;
    }
    public class BulletedListView extends View {
        private int time = 0;
        private float hn = 0,maxHn = 0,hnDir = 1;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private boolean isAnimated = false;
        private List<BulletedElement> elements = new ArrayList<>();
        private Triangle triangle;
        public BulletedListView(Context context) {
            super(context);
        }

        public void onDraw(Canvas canvas) {
            int w = canvas.getWidth(),h = canvas.getHeight();
            int n = 2;
            float h1 = h/(n+items.size());
            maxHn = h1*(n-1+items.size());
            float y_item = 2*h1;
            if(time == 0) {
                triangle = new Triangle((4*w)/5,h1/4,h1/3);
                elements.add(new BulletedElement(currentItem,h1,h1,w));
                for(String item:items) {
                    elements.add(new BulletedElement(item,y_item,h1,w));
                    y_item +=h1;
                }
            }
            paint.setColor(Color.WHITE);
            canvas.drawRect(new RectF(0,0,w,h1),paint);
            paint.setColor(Color.BLACK);
            canvas.drawLine(w/20,h1,19*w/20,h1,paint);
            paint.setTextSize(h1/3);
            canvas.drawText(currentItem,w/2-paint.measureText(currentItem),h1/2,paint);
            triangle.render(canvas,paint);
            Path path = new Path();
            path.addRect(new RectF(0,h1,w,h1+hn), Path.Direction.CCW);
            canvas.clipPath(path);
            for(BulletedElement element:elements) {
                element.draw(canvas,paint);
            }
            time++;
            if(isAnimated) {
                hn+=hnDir*(h-h1)/6;
                if(hn <= 0 && hnDir == -1) {
                    hn = 0;
                    isAnimated = false;
                    triangle.setDeg(180);
                    triangle.setRot(0);
                }
                else if(hn>=maxHn && hnDir == 1) {
                    hn = maxHn;
                    isAnimated = false;
                    triangle.setRot(0);
                    triangle.setDeg(0);
                }
                try {
                    Thread.sleep(50);
                    invalidate();
                }
                catch(Exception ex) {

                }
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated) {
                if(hn == 0) {
                    hnDir = 1;
                    isAnimated = true;
                    triangle.startAnimating();
                    postInvalidate();
                }
                else  {
                    for(BulletedElement element:elements) {
                        if(element.containsTap(event.getY())) {
                            currentItem = element.getItem();
                            isAnimated = true;
                            hnDir = -1;
                            break;
                        }
                    }
                    if(hnDir == -1 && isAnimated) {
                        triangle.startAnimating();
                        postInvalidate();
                    }
                }
            }
            return true;
        }
    }
    private class Triangle  {
        private float deg = 180,x,y,rot=0,r;
        public Triangle(float x,float y,float r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }
        public void render(Canvas canvas,Paint paint) {
            paint.setColor(Color.BLACK);

            float theta = 30+deg;
            Path path = new Path();
            for(int i=0;i<=3;i++) {
                float x1 = (float)(x+r*Math.cos(theta*Math.PI/180)),y1 = (float)(y+r*Math.sin(theta*Math.PI/180));
                if(i == 0) {
                    path.moveTo(x1,y1);
                }
                else {
                    path.lineTo(x1,y1);
                }
                theta = theta+i*120;
                theta%=360;
            }
            canvas.drawPath(path,paint);
            canvas.restore();
            deg+=rot;
            rot = deg%180 == 0?0:rot;
        }
        public void setRot(float rot) {
            this.rot = rot;
        }
        public void setDeg(float deg) {
            this.deg = deg;
        }
        public void startAnimating() {
            if(rot == 0 && deg%180 == 0) {
                rot = deg == 180?-30:30;
            }
        }
    }
    private class BulletedElement {
        private String item;
        private float y,h,w;
        public BulletedElement(String item,float y,float h,float w) {
            this.item = item;
            this.y = y;
            this.h = h;
            this.w = w;
        }
        public String getItem() {
            return item;
        }
        public void draw(Canvas canvas,Paint paint) {
            paint.setColor(Color.WHITE);
            canvas.drawRect(0,y,w,y+h,paint);
            paint.setColor(color);
            canvas.drawCircle(w/10,y+h/4,w/40,paint);
            canvas.drawLine(w/20,y+h,19*w/20,y+h,paint);
            paint.setTextSize(h/3);
            canvas.drawText(item,w/2-paint.measureText(item),y+h/2,paint);
        }
        public boolean containsTap(float y) {
            return y>this.y && y<this.y+this.h;
        }
        public int hashCode() {
            return item.hashCode()+(int)y+(int)h;
        }
    }
}
