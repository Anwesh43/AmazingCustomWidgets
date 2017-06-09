package com.anwesome.ui.rectbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 09/06/17.
 */

public class RectButtonView extends View {
    private int time = 0,w,h;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private AnimationHandler animationHandler;
    private RectBall rectBall;
    private RectShape rectShape;
    public RectButtonView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            rectBall = new RectBall();
            rectShape = new RectShape();
            animationHandler = new AnimationHandler();
        }
        rectBall.draw(canvas);
        rectShape.draw(canvas);
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN && animationHandler != null) {
            animationHandler.start();
        }
        return true;
    }
    public void update(float factor) {
        if(rectBall != null && rectShape != null) {
            rectBall.update(factor);
            rectShape.update(factor);
        }
        postInvalidate();
    }
    private class RectBall {
        private float rot = 0,y = 0;
        public void draw(Canvas canvas) {
            float radius = w/15;
            paint.setColor(Color.BLUE);
            paint.setStyle(Paint.Style.FILL);
            for(int i=0;i<4;i++) {
                canvas.save();
                canvas.translate(w / 2, h/2);
                canvas.rotate((-90+i*90)+45);
                canvas.save();
                canvas.translate(0,y);
                canvas.save();
                canvas.rotate(rot);
                paint.setColor(Color.WHITE);
                paint.setStrokeCap(Paint.Cap.ROUND);
                canvas.drawLine(0,-radius/2,0,radius/2,paint);
                for(int j=0;j<2;j++) {
                    canvas.save();
                    canvas.translate(0,-radius/2);
                    canvas.rotate((1-2*j)*45);
                    canvas.drawLine(0,0,0,radius/8,paint);
                    canvas.restore();
                }
                canvas.restore();
                canvas.drawCircle(0,0,radius,paint);
                canvas.restore();
                canvas.restore();
            }
        }
        public void update(float factor) {
            rot = 180*factor;
            y = (h/3-w/15)*factor;
        }
    }
    private class RectShape {
        private float scale = 0;
        public void draw(Canvas canvas) {
            paint.setColor(Color.BLUE);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(w/12);
            canvas.save();
            canvas.translate(w/2,h/2);
            canvas.scale(scale,scale);
            canvas.drawRoundRect(new RectF(-h/3,-h/3,h/3,h/3),h/15,h/15,paint);
            canvas.restore();
        }
        public void update(float factor) {
            scale = factor;
        }
    }
    private class AnimationHandler extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener{
        private ValueAnimator startAnim = ValueAnimator.ofFloat(0,1),endAnim = ValueAnimator.ofFloat(1,0);
        private int dir = 0;
        private boolean isAnimating = false;
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if(isAnimating) {
                update((float)valueAnimator.getAnimatedValue());
            }
        }
        public void onAnimationEnd(Animator animator) {
            if(isAnimating) {
                dir = dir == 0?1:0;
                isAnimating = false;
            }
        }
        public void start() {
            if(!isAnimating) {
                if(dir == 0) {
                    startAnim.start();
                }
                if(dir == 1) {
                    endAnim.start();
                }
                isAnimating = true;
            }
        }
        public AnimationHandler() {
            startAnim.setDuration(500);
            endAnim.setDuration(500);
            startAnim.addUpdateListener(this);
            endAnim.addUpdateListener(this);
            startAnim.addListener(this);
            endAnim.addListener(this);
        }
    }
}
