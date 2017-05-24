package com.anwesome.ui.circularystbuttonlist;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 25/05/17.
 */

public class CircularYSTButtonView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int time = 0,w,h;
    private ClipBoard clipBoard;
    private Indicator indicator;
    private AnimationHandler animationHandler = new AnimationHandler();
    public CircularYSTButtonView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            clipBoard = new ClipBoard();
            indicator = new Indicator();
        }
        clipBoard.draw(canvas);
        indicator.draw(canvas);
        time++;
    }
    public void update(float factor) {
        clipBoard.update(factor);
        indicator.update(factor);
        postInvalidate();
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            animationHandler.start();
        }
        return true;
    }
    private class ClipBoard {
        private float scale = 0,x,y,size;
        public ClipBoard() {
            x = w/2;
            y = h/2;
            size = Math.min(w,h)*0.8f;
        }
        public void draw(Canvas canvas) {
            paint.setStrokeWidth(size/10);
            canvas.save();
            canvas.translate(x,y);
            paint.setColor(Color.GRAY);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawRoundRect(new RectF(-size/2,-size/2,size/2,size/2),size/20,size/20,paint);
            int n = 5;
            canvas.save();
            canvas.translate(-size/2,-size/2);
            float circleX = size/10,gap = size/11,y_gap = size/(2*n+1);
            for(int i=0;i<n;i++) {
                canvas.drawCircle(circleX,y_gap,size/40,paint);
                canvas.drawLine(circleX+size/5,y_gap,circleX+size*0.9f,y_gap,paint);
                y_gap += 2*gap;
            }
            canvas.restore();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.argb(150,0,0,0));
            canvas.save();
            canvas.scale(scale,scale);
            canvas.drawRoundRect(new RectF(-size/2,-size/2,size/2,size/2),size/20,size/20,paint);
            canvas.restore();
            canvas.restore();
        }
        public void update(float factor) {
            scale = factor;
        }
    }
    private class Indicator {
        float deg = 0;
        public void draw(Canvas canvas) {
            paint.setColor(Color.parseColor("#e53935"));
            paint.setStyle(Paint.Style.FILL);
            canvas.drawArc(new RectF(w/2-w/20,0.9f*h-w/20,w/2+w/20,0.9f*h+w/20),0,deg,true,paint);
        }
        public void update(float factor) {
            deg = 360*factor;
        }
    }
    private class AnimationHandler extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {
        private boolean isAnimating = false;
        private int dir = 0;
        private ValueAnimator startAnim = ValueAnimator.ofFloat(0,1),endAnim = ValueAnimator.ofFloat(1,0);
        public AnimationHandler() {
            startAnim.setDuration(500);
            endAnim.setDuration(500);
            startAnim.addListener(this);
            endAnim.addListener(this);
            startAnim.addUpdateListener(this);
            endAnim.addUpdateListener(this);
        }
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float factor = (float)valueAnimator.getAnimatedValue();
            update(factor);
        }
        public void onAnimationEnd(Animator animator) {
            if(isAnimating) {
                if(dir == 0) {

                }
                else {

                }
                dir = dir == 0?1:0;
                isAnimating = false;
            }
        }
        public void start() {
            if(!isAnimating) {
                if(dir == 0) {
                    startAnim.start();
                }
                else {
                    endAnim.start();
                }
                isAnimating = true;
            }
        }
    }
}
