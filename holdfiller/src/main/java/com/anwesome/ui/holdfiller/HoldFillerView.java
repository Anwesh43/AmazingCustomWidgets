package com.anwesome.ui.holdfiller;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 10/06/17.
 */

public class HoldFillerView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int time = 0,w,h;
    private HoldCircleButton holdCircleButton;
    private FillRect fillRect;
    private FillAnimationHandler fillAnimationHandler;
    public HoldFillerView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            holdCircleButton = new HoldCircleButton();
            fillRect = new FillRect();
            fillAnimationHandler = new FillAnimationHandler();
        }
        canvas.drawColor(Color.BLACK);
        paint.setColor(Color.YELLOW);
        holdCircleButton.draw(canvas);
        time++;
        if(fillAnimationHandler != null) {
            fillAnimationHandler.animate();
        }
    }
    public void update() {
        if(fillRect!=null) {
            fillRect.update();
            invalidate();
        }
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN && holdCircleButton != null && fillAnimationHandler!=null) {
            if(holdCircleButton.handleTap(event.getX(),event.getY())) {
                fillAnimationHandler.startFilling();
            }
        }
        if(event.getAction() == MotionEvent.ACTION_UP && holdCircleButton != null) {
            fillAnimationHandler.stopFilling();
            holdCircleButton.setFill(false);
        }
        return true;
    }
    private class HoldCircleButton {
        private float x,y,r;
        private boolean fill = false;
        public HoldCircleButton() {
            x = w/2;
            y = h/10;
            r = h/20;
        }
        public void setFill(boolean fill) {
            this.fill = fill;
        }
        public void draw(Canvas canvas) {
            paint.setStrokeWidth(r/10);
            if(!fill) {
                paint.setStyle(Paint.Style.STROKE);
            }
            else {
                paint.setStyle(Paint.Style.FILL);
            }
            canvas.drawCircle(x,y,r,paint);
        }
        public boolean handleTap(float x,float y) {
            boolean condition =  x>=this.x-r && x<=this.x+r && y>=this.y-r && y<=this.y+r;
            if(condition) {
                setFill(true);
            }
            return condition;
        }
    }
    private class FillRect {
        private int dir = 0,y;
        public boolean isStopped() {
            return dir == 0;
        }
        public void draw(Canvas canvas) {
            paint.setStyle(Paint.Style.FILL);
            canvas.save();
            canvas.translate(w/2,h);
            canvas.drawRect(new RectF(-2*w/5,h-y,2*w/5,h),paint);
            canvas.restore();
        }
        public void setDir(int dir) {
            this.dir = dir;
        }
        public void update() {
            y+= (h/10)*dir;
            if(y > 4*h/5) {
                y = 4*h/5;
            }
            if(y<0) {
                y = 0;
                dir = 0;
            }
        }
    }
    private class FillAnimationHandler  {
        private boolean isAnimated = false;
        public void animate() {
            if(isAnimated) {
                try {
                    Thread.sleep(50);
                }
                catch (Exception ex) {

                }
                update();
                if(fillRect!=null && fillRect.isStopped()) {
                    isAnimated = false;
                }
            }
        }
        public void startFilling() {
            if(fillRect!=null) {
                fillRect.setDir(1);
                if(!isAnimated) {
                    isAnimated = true;
                    postInvalidate();
                }
            }
        }
        public void stopFilling() {
            if(fillRect!=null) {
                fillRect.setDir(-1);
            }
        }
    }
}
