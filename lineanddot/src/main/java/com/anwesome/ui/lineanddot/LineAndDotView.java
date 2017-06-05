package com.anwesome.ui.lineanddot;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 06/06/17.
 */

public class LineAndDotView extends View{
    private int time = 0,w,h;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public LineAndDotView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
        }
        paint.setStrokeWidth(Math.max(w,h)/100);
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {

        }
        return true;
    }
    private class AnimationHandler {
        private boolean isAnimated = false;
        public void animate() {
            if(isAnimated) {
                try {
                    Thread.sleep(50);
                    invalidate();
                }
                catch (Exception ex) {

                }
            }
        }
        public void handleTap(float x,float y) {

        }
    }
    private class Line {
        private float y,x,lx = 0;
        private boolean stopped = false;
        public Line(float y) {
            x = w/10;
            this.y = y;
        }
        public void update(float dir) {
            lx += 0.2f*dir;
            if(lx>=0.8f*w && lx<=0) {
                stopped = true;
            }
        }
        public boolean isStopped() {
            return stopped;
        }
        public void draw(Canvas canvas) {
            canvas.drawLine(x,y,x+lx,y,paint);
        }
    }
}
