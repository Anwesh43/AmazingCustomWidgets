package com.anwesome.app.prokbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

/**
 * Created by anweshmishra on 12/02/17.
 */
public class ProkButton {
    private Activity activity;
    private ProkButtonView prokButtonView;
    private int color = Color.parseColor("#00695C");
    public ProkButton(Activity activity) {
        this.activity = activity;
    }
    public void show(int x,int y) {
        if(prokButtonView == null) {
            prokButtonView = new ProkButtonView(activity);
            Point size = DimensionsUtil.getDeviceDimension(activity);
            int w = size.x,h = size.y,dimension = h/6;
            if(w>h) {
                dimension = w/6;
            }
            activity.addContentView(prokButtonView,new ViewGroup.LayoutParams(dimension,dimension));
        }
        prokButtonView.setX(x);
        prokButtonView.setY(y);

    }
    private class ProkButtonView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private int deg = 0,dir = 0;
        private boolean isAnimated = false;
        public ProkButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            paint.setColor(color);
            int w = canvas.getWidth()/2,h = canvas.getHeight()/2;
            int x = w/2,y = 2*h/3,startDeg = -60,endDeg = 240,r = h/3;
            canvas.save();
            canvas.translate(x,y);
            canvas.rotate(deg);
            Path path = new Path();
            path.moveTo(x+r*(float)Math.cos(startDeg*Math.PI/180),y+r*(float)Math.sin(startDeg*Math.PI/180));
            for(int i = startDeg;i<=endDeg;i++) {
                path.lineTo(r*(float)Math.cos(i*Math.PI/180),r*(float)Math.sin(i*Math.PI/180));
            }
            path.lineTo(0,-2*h/3);
            path.lineTo(r*(float)Math.cos(startDeg*Math.PI/180),r*(float)Math.sin(startDeg*Math.PI/180));
            canvas.drawPath(path,paint);
            canvas.restore();
            if(isAnimated) {
                deg+=dir*20;
                if(deg>=120) {
                    dir = -1;
                }
                if(deg<=0) {
                    dir = 0;
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
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated && dir == 0) {
                dir = 1;
                isAnimated = true;
                postInvalidate();
            }
            return true;
        }
    }
}
