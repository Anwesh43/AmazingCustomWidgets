package com.anwesome.app.buttonintriangle;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.*;

/**
 * Created by anweshmishra on 14/02/17.
 */
public class ButtonsTriangle {
    private Activity activity;
    private ButtonsTriangleView view;
    public ButtonsTriangle(Activity activity) {
        this.activity = activity;
    }
    public void show(int x,int y) {
        if(view == null) {
            view = new ButtonsTriangleView(activity);
            activity.addContentView(view,new ViewGroup.LayoutParams(300,300));
        }
        view.setX(x);
        view.setY(y);
    }
    private class ButtonsTriangleView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private float deg = 0,dir = 0;
        private boolean isAnimated = false;
        public ButtonsTriangleView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            int w = canvas.getWidth() / 2, h = canvas.getHeight() / 2;
            canvas.save();
            canvas.translate(w, h);
            canvas.rotate(deg);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.parseColor("#1565C0"));
            Path path = new Path();
            path.moveTo(0, -w / 2);
            path.lineTo(-w / 2, w / 2);
            path.lineTo(w / 2, w / 2);
            path.lineTo(0, -w / 2);
            canvas.drawPath(path, paint);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.parseColor("#e53935"));
            canvas.drawCircle(0, 0, w / 6, paint);
            canvas.restore();
            if(isAnimated) {
                deg+=dir*20;
                if(deg%120 == 0) {
                    dir = 0;
                    isAnimated = false;
                }
                try {
                    Thread.sleep(50);
                    invalidate();
                }
                catch (Exception ex) {

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
