package com.anwesome.ui.arrowlinebutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 19/06/17.
 */

public class ArrowLineButton {
    public static class ArrowLineButtonView extends View {
        private int time = 0,w,h;
        private ArrowLine arrowLine;
        private AnimationHandler animationHandler;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public ArrowLineButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(time == 0) {
                w = canvas.getWidth();
                h = canvas.getHeight();
                arrowLine = new ArrowLine();
                animationHandler = new AnimationHandler(this);
            }
            arrowLine.draw(canvas,paint,w/2,h/2,Math.min(w,h)/3);
            time++;
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && animationHandler != null) {
                animationHandler.start();
            }
            return true;
        }
        public void update(float factor) {
            if(arrowLine != null) {
                arrowLine.update(Math.min(w,h)/3,factor);
            }
            postInvalidate();
        }
    }
    public static class ArrowLine {
        private float lx = 0;
        public void draw(Canvas canvas,Paint paint,float x,float y,float size) {
            for(int i=0;i<4;i++) {
                canvas.save();
                canvas.translate(x, y);
                canvas.rotate(i*90);
                Path path = new Path();
                path.moveTo(0,0);
                path.lineTo(lx,-size/3);
                path.lineTo(0,-size/3);
                canvas.drawPath(path,paint);
                canvas.restore();
            }
        }
        public void update(float maxLx,float factor) {
            lx = maxLx * factor;
        }
    }
    private static class AnimationHandler extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener{
        private ValueAnimator startAnim = ValueAnimator.ofFloat(0,1),endAnim = ValueAnimator.ofFloat(1,0);
        private boolean isAnimated = false;
        private int dir = 0;
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if(isAnimated) {
                view.update((float)valueAnimator.getAnimatedValue());
            }
        }
        public void onAnimationEnd(Animator animator) {
            if(isAnimated) {
                dir = dir == 0?1:0;
                isAnimated = false;
            }
        }
        public void start() {
            if(!isAnimated) {
                if(dir == 0) {
                    startAnim.start();
                }
                else {
                    endAnim.start();
                }
                isAnimated = true;
            }
        }
        private ArrowLineButtonView view;
        public AnimationHandler(ArrowLineButtonView arrowLineButtonView) {
            this.view = arrowLineButtonView;
            startAnim.addListener(this);
            endAnim.addListener(this);
            startAnim.addUpdateListener(this);
            endAnim.addUpdateListener(this);
            startAnim.setDuration(500);
            endAnim.setDuration(500);
        }
    }
}
