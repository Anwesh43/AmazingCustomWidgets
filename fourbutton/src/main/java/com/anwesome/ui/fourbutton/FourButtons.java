package com.anwesome.ui.fourbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.hardware.display.DisplayManager;
import android.view.*;
import java.util.*;

/**
 * Created by anweshmishra on 04/02/17.
 */
public class FourButtons {
    private Activity activity;
    private List<FourButtonElement> elements = new ArrayList<>();
    private FourButtonsView fourButtonsView;
    private boolean isAnimated = false;
    private int w=100,h=100,xDir=0,yDir=0;
    public FourButtons(Activity activity) {
        this.activity = activity;
        this.fourButtonsView = new FourButtonsView(activity);
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
    public void addButton(String action,Bitmap bitmap,FourButtonListener fourButtonListener) {
        FourButtonElement element = new FourButtonElement(action,bitmap);
        element.setClickListener(fourButtonListener);
        elements.add(element);
    }
    public void show() {
        if(elements.size() == 4) {
            fourButtonsView= new FourButtonsView(activity);
            activity.addContentView(fourButtonsView,new ViewGroup.LayoutParams(w,h));
        }
    }
    public void dismiss() {
        if(fourButtonsView!=null) {
            fourButtonsView.setVisibility(View.INVISIBLE);
            fourButtonsView = null;
        }
    }
    private class FourButtonElement {
        private String action;
        private Bitmap bitmap;
        private FourButtonListener clickListener;
        private float x,y,scale=0.5f,r;
        public FourButtonElement(String action, Bitmap bitmap) {
            this.action = action;
            this.bitmap = bitmap;
        }
        public void setX(float x) {
            this.x = x;
        }
        public void setY(float y) {
            this.y = y;
        }
        public void setR(float r) {
            this.r = r;
            bitmap = Bitmap.createScaledBitmap(bitmap,(int)r/2,(int)r/2,true);
        }
        public void setScale(float scale) {
            this.scale = scale;
        }
        public void setClickListener(FourButtonListener clickListener) {
            this.clickListener = clickListener;
        }
        public void draw(Canvas canvas,Paint paint) {

            canvas.save();
            canvas.translate(x,y);
            canvas.scale(scale,scale);
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(15);
            canvas.drawCircle(0,0,r/2,paint);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawBitmap(bitmap,-bitmap.getWidth()/2,-bitmap.getHeight()/2,paint);
            canvas.restore();
        }
        public void move(float speed) {
            this.x+=speed*xDir;
            this.y+=speed*yDir;
            this.scale+=0.1f;
            if((this.x>=w/2 && xDir == 1) || (this.x<=w/2 && xDir == -1) || (this.y>=h/2 && yDir == 1) || (this.y<=h/2 && yDir == -1)) {
                this.scale = 1;
                this.x = w/2;
                this.y = h/2;
                xDir = 0;
                yDir = 0;
            }
        }
        public boolean handleTap(float x,float y) {
            boolean insideButton = x>=this.x-this.r && x<=this.x+this.r && y>=this.y-this.r && y<=this.y+this.r;
            if(insideButton && !isAnimated) {
                xDir = w/2>this.x?1:-1;
                yDir = h/2>this.y?1:-1;
            }
            return insideButton;
        }
        public FourButtonListener getClickListener() {
            return clickListener;
        }
        public int hashCode() {
            return action.hashCode()+this.bitmap.hashCode()+(int)x+(int)y;
        }

    }
    public interface FourButtonListener {
        void onClick(View view);
    }
    private class FourButtonsView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private int time = 0,speed=0;
        private FourButtonElement selectedObject = null;
        public FourButtonsView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            canvas.drawColor(Color.parseColor("#77000000"));
            int r = canvas.getWidth()/3;
            init(r);
            renderElements(canvas);
            time++;
            if(isAnimated && xDir != 0 && yDir != 0) {
                try {
                    Thread.sleep(50);
                    invalidate();
                }
                catch (Exception ex) {

                }
            }
        }
        public void renderElements(Canvas canvas) {
            if(!isAnimated) {
                for(FourButtonElement element:elements) {
                    element.draw(canvas,paint);
                }
            }
            else {
                if(selectedObject!=null) {
                    selectedObject.draw(canvas,paint);
                    selectedObject.move(speed);
                }
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                if(!isAnimated) {
                    for(FourButtonElement element:elements) {
                        if(element.handleTap(event.getX(),event.getY())) {
                            selectedObject = element;
                            isAnimated = true;
                            postInvalidate();
                            break;
                        }
                    }
                }
                else {
                    if(selectedObject!=null && xDir == 0 && yDir == 0) {
                        if(selectedObject.handleTap(event.getX(),event.getY())) {
                            if(selectedObject.getClickListener()!=null) {
                                selectedObject.getClickListener().onClick(this);
                            }
                            dismiss();
                        }
                    }
                }
            }
            return true;
        }
        public void init(int r) {
            if(time == 0) {
                speed = r/5;
                int i = 0;
                for (FourButtonElement fourButtonElement : elements) {
                    fourButtonElement.setR(r);
                    fourButtonElement.setScale(0.5f);
                    fourButtonElement.setX(w/2-r+2*r*(i%2));
                    fourButtonElement.setY(h/2-r+2*r*(i/2));
                    i++;
                }
            }
        }
    }
}
