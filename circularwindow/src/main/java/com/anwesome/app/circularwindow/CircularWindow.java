package com.anwesome.app.circularwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.*;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

/**
 * Created by anweshmishra on 11/02/17.
 */
public class CircularWindow {
    private int color = Color.parseColor("#0B4762");
    private Activity activity;
    private CircularWindowView circularWindowView;
    private int w,h;
    public CircularWindow(Activity activity) {
        this.activity = activity;
    }
    public void setColor(int color) {
        this.color = color;
    }
    public void show(int x,int y) {
        if(circularWindowView==null) {
            Point size = DimensionsUtil.getDeviceDimension(activity);
            int dimension = size.y/4;
            if(size.x>size.y) {
                dimension = size.x/4;
            }
            circularWindowView = new CircularWindowView(activity);
            activity.addContentView(circularWindowView,new ViewGroup.LayoutParams(dimension,dimension));
        }
        circularWindowView.setX(x);
        circularWindowView.setY(y);
    }
    private class CircularWindowView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private boolean isAnimated = false;
        private float deg = 0,dir =0;
        public CircularWindowView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            int w= canvas.getWidth(),h = canvas.getHeight();
            canvas.save();
            canvas.translate(w/2,h/2);
            canvas.rotate(deg);
            paint.setColor(color);
            canvas.drawCircle(0,0,w/2,paint);
            paint.setColor(Color.WHITE);
            int xl[] = {-1,1,-1,1},yl[] = {-1,-1,1,1};
            for(int i=0;i<4;i++) {
                float x = w/8*xl[i],y = w/8*yl[i],x1 = x+w/8,y1 = y+h/8;
                canvas.drawRoundRect(new RectF(x,y,x1,y1),w/32,h/32,paint);
            }
            canvas.restore();
            try {
                deg+=dir*10;
                if(deg>=60) {
                    dir = -1;
                }
                if(deg<=0) {
                    deg = 0;
                    dir = 0;
                    isAnimated = false;
                }
                Thread.sleep(50);
                invalidate();
            }
            catch(Exception ex) {

            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated && dir == 0) {
                dir = 1;
                isAnimated = true;
                postInvalidate();
            }
            return true;
        }
    }
}
