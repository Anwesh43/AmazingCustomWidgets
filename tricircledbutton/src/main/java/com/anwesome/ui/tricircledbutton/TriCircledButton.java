package com.anwesome.ui.tricircledbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.*;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

/**
 * Created by anweshmishra on 10/02/17.
 */
public class TriCircledButton {
    private Activity activity;
    private TriCircledButtonView circledButtonView;
    private int triangleColor=Color.parseColor("#2196F3"),circleColor = Color.parseColor("#ffffff");
    public TriCircledButton(Activity activity) {
        this.activity = activity;
    }
    public void show(int x,int y) {
        if(circledButtonView == null) {
            circledButtonView = new TriCircledButtonView(activity);
            Point size = DimensionsUtil.getDeviceDimension(activity);
            int w = size.x,h = size.y;
            activity.addContentView(circledButtonView,new ViewGroup.LayoutParams(h/4,h/4));
        }
        circledButtonView.setX(x);
        circledButtonView.setY(y);
    }
    public void setTriangleColor(int triangleColor) {
        this.triangleColor = triangleColor;
    }
    public void setCircleColor(int circleColor) {
        this.circleColor = circleColor;
    }
    private class TriCircledButtonView extends View {
        private float deg = 0,dir = 0;
        private boolean shouldAnimate = false;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public  TriCircledButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            int w = canvas.getWidth(),h = canvas.getHeight();
            canvas.save();
            canvas.translate(w/2,h/2);
            canvas.rotate(deg);
            Path path = new Path();
            path.lineTo(w/2,h/2);
            path.lineTo(0,-h/2);
            path.lineTo(-w/2,h/2);
            path.lineTo(w/2,h/2);
            paint.setColor(triangleColor);
            canvas.drawPath(path,paint);
            paint.setColor(circleColor);
            canvas.drawCircle(w/4,h/4,w/8,paint);
            canvas.drawCircle(-w/4,h/4,w/8,paint);
            canvas.drawCircle(0,-h/4,w/8,paint);
            canvas.restore();
            if(shouldAnimate) {
                deg+=dir*15;
                if(deg>=60) {
                    dir = -1;
                }
                if(deg<=0) {
                    dir = 0;
                    deg = 0;
                    shouldAnimate = false;
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
            if(event.getAction() == MotionEvent.ACTION_DOWN && !shouldAnimate && dir == 0) {
                dir = 1;
                shouldAnimate = true;
            }
            return true;
        }
    }
}
