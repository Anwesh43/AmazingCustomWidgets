package com.anwesome.ui.cornercenterball;

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
 * Created by anweshmishra on 13/06/17.
 */

public class CornerCenterBallView extends View {
    private int time = 0,w,h;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private CornerCenterBall cornerCenterBall;
    private AnimationHandler animationHandler;
    public CornerCenterBallView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            cornerCenterBall = new CornerCenterBall();
            animationHandler = new AnimationHandler();
        }
        cornerCenterBall.draw(canvas);
        time++;
    }
    public void update(float factor) {
        cornerCenterBall.update(factor);
        postInvalidate();
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN && animationHandler!=null) {
            animationHandler.start();
        }
        return true;
    }
    private class CornerCenterBall {
        private int index = 0;
        private float r = 0;
        public void draw(Canvas canvas) {
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLUE);
            canvas.drawRect(new RectF(-w/3,-h/3,w/3,h/3),paint);
            for(int i=0;i<4;i++) {
                canvas.save();
                canvas.rotate(i*90-45);
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.BLUE);
                canvas.drawCircle(0,-h/3,w/15,paint);
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.WHITE);
                canvas.drawCircle(0,-h/3,w/15,paint);
                canvas.restore();
            }
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.BLUE);
            canvas.save();
            canvas.translate(w/2,h/2);
            canvas.rotate(index*90-45);
            canvas.drawCircle(0,r,w/15,paint);
            canvas.restore();
        }
        public void update(float factor) {
            r = -h/3*factor;
        }
        public void incrementIndex() {
            index++;
            if(index%4 == 0) {
                index = 0;
            }
        }
    }
    private class AnimationHandler extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener{
        private ValueAnimator startAnim = ValueAnimator.ofFloat(0,1),endAnim = ValueAnimator.ofFloat(1,0);
        private int dir = 0;
        private boolean isAnimating = false;
        public AnimationHandler() {
            startAnim.setDuration(500);
            endAnim.setDuration(500);
            startAnim.addListener(this);
            endAnim.addListener(this);
            startAnim.addUpdateListener(this);
            endAnim.addUpdateListener(this);
        }
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if(isAnimating) {
                update((float)valueAnimator.getAnimatedValue());
            }
        }
        public void onAnimationEnd(Animator animator) {
            if(isAnimating) {
                dir = dir == 0?1:0;
                isAnimating = false;
                if(cornerCenterBall != null) {
                    cornerCenterBall.incrementIndex();
                }
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
