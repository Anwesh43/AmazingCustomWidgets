package com.anwesome.ui.bulletedlist;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.MotionEvent;
import android.view.View;
import java.util.*;

/**
 * Created by anweshmishra on 06/02/17.
 */
public class BulletedList {
    private String currentItem = "Select";
    private Activity activity;
    private int color = Color.BLACK;
    private List<String> items = new ArrayList<>();
    public BulletedList(Activity activity) {
        this.activity = activity;
    }
    public void show() {

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
                triangle = new Triangle(9*w/10,h1/4,h1/6);
                elements.add(new BulletedElement(currentItem,h1,h1,w));
                for(String item:items) {
                    elements.add(new BulletedElement(item,y_item,h1,w));
                }
            }
            paint.setColor(Color.WHITE);
            canvas.drawRect(new RectF(0,0,w,h1),paint);
            canvas.drawLine(w/20,h1,19*w/20,h1,paint);
            paint.setTextSize(h1/3);
            canvas.drawText(currentItem,w/2-paint.measureText(currentItem),h1/2,paint);
            triangle.render(canvas,paint);
            Path path = new Path();
            path.addRect(new RectF(0,h1,w,h1+hn), Path.Direction.CCW);
            for(BulletedElement element:elements) {
                element.draw(canvas,paint);
            }
            time++;
            if(isAnimated) {
                hn+=hnDir*(h1*(n-1+items.size()))/3;
                if(hn <= 0 && hnDir == -1) {
                    hn = 0;
                    isAnimated = false;
                }
                else if(hn>=maxHn && hnDir == 1) {
                    hn = maxHn;
                    isAnimated = false;
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
                        if(hnDir == -1 && isAnimated) {
                            triangle.startAnimating();
                            postInvalidate();
                        }
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
            canvas.save();
            canvas.translate(x,y);
            canvas.rotate(deg);
            int theta = 30;
            Path path = new Path();
            for(int i=0;i<=3;i++) {
                theta = theta+i*120;
                float x1 = (float)(r*Math.cos(theta*Math.PI/180)),y1 = (float)(r*Math.sin(theta*Math.PI/180));
                if(i == 0) {
                    path.moveTo(x1,y1);
                }
                else {
                    path.lineTo(x1,y1);
                }
                canvas.drawPath(path,paint);
            }
            canvas.restore();
            deg+=rot;
            rot = deg%180 == 0?0:rot;
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
            paint.setColor(color);
            canvas.drawCircle(w/10,h/4,w/20,paint);
            canvas.drawLine(w/20,h,19*w/20,h,paint);
            paint.setTextSize(h/3);
            canvas.drawText(item,w/2-paint.measureText(item),h/2,paint);
        }
        public boolean containsTap(float y) {
            return y>this.y && y<this.y+this.h;
        }
        public int hashCode() {
            return item.hashCode()+(int)y+(int)h;
        }
    }
}
