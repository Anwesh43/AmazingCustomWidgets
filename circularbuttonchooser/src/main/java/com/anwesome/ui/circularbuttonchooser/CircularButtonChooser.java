package com.anwesome.ui.circularbuttonchooser;

import android.app.Activity;
import android.graphics.*;
import android.hardware.display.DisplayManager;
import android.view.*;
import android.content.*;

import java.util.*;

/**
 * Created by anweshmishra on 05/02/17.
 */
public class CircularButtonChooser {
    private Activity activity;
    private boolean isAnimated = false;
    private int w=1000,h=1600;
    private List<CircularButton> circularButtons = new ArrayList<>();
    public CircularButtonChooser(Activity activity) {
        this.activity = activity;
        initDimensions();
    }
    public void initDimensions() {
        DisplayManager displayManager = (DisplayManager)activity.getSystemService(Context.DISPLAY_SERVICE);
        Display display = displayManager.getDisplay(0);
        if(display != null) {
            Point size = new Point();
            display.getRealSize(size);
            w = size.x;
            h = size.y;
        }
    }
    public void addCircularButton(Bitmap bitmap, View.OnClickListener onClickListener) {
        circularButtons.add(new CircularButton(bitmap,onClickListener));
    }
    public void show() {
        CircularButtonChooserView circularButtonChooserView = new CircularButtonChooserView(activity);
        activity.addContentView(circularButtonChooserView,new ViewGroup.LayoutParams(2*w/3,2*w/3));
    }
    public void show(int x,int y) {
        CircularButtonChooserView circularButtonChooserView = new CircularButtonChooserView(activity);
        circularButtonChooserView.setX(x);
        circularButtonChooserView.setY(y);
        activity.addContentView(circularButtonChooserView,new ViewGroup.LayoutParams(2*w/3,2*w/3));
    }
    public class CircularButtonChooserView extends View {
        private int time = 0;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private float currDeg = 0,rotSpeed,dir = 1;
        private CircularButton selectedButton;
        public CircularButtonChooserView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            paint.setColor(Color.parseColor("#757575"));
            paint.setStrokeWidth(15);
            int w = canvas.getWidth(),h = canvas.getHeight(),r = w/2;
            if(h<w) {
                r = h/2;
            }
            init(w,h,r);

            canvas.save();
            canvas.translate(w/2,h/2);
            canvas.save();
            canvas.rotate(currDeg);
            canvas.drawLine(0,0,(r*4)/5,0,paint);
            canvas.restore();
            drawButtons(canvas);
            canvas.restore();
            time++;
            if(isAnimated) {
                currDeg+=rotSpeed*dir;
                if(currDeg<0) {
                    currDeg+=360;

                }
                currDeg%=360;
                if(selectedButton!=null && (currDeg>=selectedButton.getDeg()-rotSpeed/5 && currDeg<=selectedButton.getDeg()+rotSpeed/5)){
                    isAnimated = false;
                    currDeg = selectedButton.getDeg();
                    if(selectedButton.getOnClickListener()!=null) {
                        selectedButton.getOnClickListener().onClick(this);
                    }
                }

                try {
                    Thread.sleep(50);
                    invalidate();
                }
                catch(Exception ex) {

                }
            }
        }
        public void init(int w,int h,int r) {
            if(time == 0 && circularButtons.size() > 0) {
                int gap = 360/(circularButtons.size());
                int deg = gap/2;
                rotSpeed = gap/10;
                for(CircularButton button:circularButtons) {
                    button.setDeg(deg);
                    button.setR(r);
                    button.setXY(w/2,h/2);
                    deg+=gap;
                }
            }
        }
        public void drawButtons(Canvas canvas) {
            for(CircularButton button:circularButtons) {
                button.draw(canvas,paint);
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX(),y = event.getY();
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated) {
                for(CircularButton circularButton:circularButtons) {
                    if(circularButton.handleTap(x,y)) {
                        selectedButton = circularButton;
                        float finalDeg = selectedButton.getDeg();
                        float theta = finalDeg-currDeg;
                        dir = theta>0?1:-1;
                        if(Math.abs(360-Math.abs(theta)) < Math.abs(theta)) {
                            dir*=-1;
                        }
                        if(theta == 0) {
                            dir = 0;
                        }
                        isAnimated = true;
                        postInvalidate();
                    }
                }
            }
            return true;
        }
    }
    public class CircularButton  {
        private Bitmap bitmap;
        private View.OnClickListener onClickListener;
        private float x=0,y=0,deg=-1,r=0;
        public CircularButton(Bitmap bitmap, View.OnClickListener onClickListener) {
            this.bitmap = bitmap;
            this.onClickListener = onClickListener;
        }
        public void draw(Canvas canvas,Paint paint) {
            canvas.save();
            canvas.rotate(deg);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(r-r/10,0,r/10,paint);
            if(bitmap!=null) {
                bitmap = Bitmap.createScaledBitmap(bitmap,(int)r/10,(int)r/10,true);
                canvas.drawBitmap(bitmap,r-r/10-r/20,-r/20,paint);
            }
            canvas.restore();
        }
        public void setXY(float x,float y) {
            if(r!=0 && deg!=-1) {
                this.x = x+(float)(r*Math.cos(deg*Math.PI/180));
                this.y = y+(float)(r*Math.sin(deg*Math.PI/180));
            }
        }
        public View.OnClickListener getOnClickListener() {
            return onClickListener;
        }
        public void setR(float r) {
            this.r = r;
        }
        public void setX(float x) {
            this.x = x;
        }
        public void setY(float y) {
            this.y = y;
        }
        public void setDeg(float deg) {
            this.deg = deg;
        }
        public float getDeg() {
            return this.deg;
        }
        public boolean handleTap(float x,float y) {
            return (x>=this.x-r && x<=this.x+r && y>=this.y-r && y<=this.y+r);
        }
        public int hashCode() {
            return bitmap.hashCode()+(int)x+(int)y+(int)deg;
        }
    }

}
