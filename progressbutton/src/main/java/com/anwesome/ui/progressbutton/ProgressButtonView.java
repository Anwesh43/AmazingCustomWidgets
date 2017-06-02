package com.anwesome.ui.progressbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 03/06/17.
 */

public class ProgressButtonView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int time = 0,w,h;
    public ProgressButtonView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
        }
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
    public void update(float factor) {
        postInvalidate();
    }
    private class CircularProgressBar {
        private float x,y,r,deg = 0;
        private int i;
        public CircularProgressBar(int i) {
            if(i == 0) {
                x = w/4;
            }
            if(i == 1) {
                x = 3*w/4;
            }
            y = h/2;
            r = w/10;
            this.i = i;
        }
        public void draw(Canvas canvas) {
            Path path = new Path();
            for(int i=0;i<=deg;i++) {
                float x = (float)(r*Math.cos(i*Math.PI/180)),y = (float)(r*Math.sin(i*Math.PI/180));
                if(i == 0) {
                    path.moveTo(x,y);
                }
                else {
                    path.lineTo(x,y);
                }
            }
            canvas.drawPath(path,paint);
        }
        public void update(float factor) {
            deg = 360*factor;
        }
        public int hashCode() {
            return (int)(x+deg);
        }
        public boolean handleTap(float x,float y) {
            boolean condition = x>=this.x-r && x>=this.x+r && y>=this.y-r && y<=this.y+r;
            if(condition) {

            }
            return condition;
        }
    }
    private class LinearBar {
        private float x,y,wx = 0;
        public LinearBar(int i) {
            x = 0;
            y = h / 3 * (i + 1);
        }
        public void update(float factor) {
            wx = w*factor;
        }
        public void draw(Canvas canvas) {
            paint.setStrokeWidth(w/30);
            canvas.drawLine(x,y,wx,y,paint);
        }
        public int hashCode() {
            return (int)(y+wx);
        }
    }
    private class AnimationHandler extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener{
        private int dir = 0;
        private boolean isAnimating = false;
        private ValueAnimator startAnim = ValueAnimator.ofFloat(0,1),endAnim = ValueAnimator.ofFloat(1,0);
        public void start() {
            if(!isAnimating) {
                if(dir == 0) {
                    startAnim.start();
                }
                else if(dir == 1) {
                    endAnim.start();
                }
                isAnimating = true;
            }
        }
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if(isAnimating) {
                update((float)valueAnimator.getAnimatedValue());
            }
        }
        public void onAnimationEnd(Animator animator) {
            if(isAnimating) {
                isAnimating = false;
                dir = dir == 0?1:0;
            }
        }
        public AnimationHandler() {
            startAnim.setDuration(500);
            startAnim.addUpdateListener(this);
            endAnim.addUpdateListener(this);
            startAnim.addListener(this);
            endAnim.addListener(this);
        }
    }
}
