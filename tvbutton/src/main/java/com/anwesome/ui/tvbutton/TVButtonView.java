package com.anwesome.ui.tvbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 28/05/17.
 */

public class TVButtonView extends View{
    private Indicator indicator;
    private Play play;
    private AnimationHandler animationHandler;
    private int time = 0,w,h,color = Color.parseColor("#0097A7");
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public TVButtonView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            indicator = new Indicator();
            play = new Play();
            animationHandler = new AnimationHandler();
        }
        int r = w/8;
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(r/3);
        canvas.save();
        canvas.translate(w/2,h/2);
        canvas.drawRoundRect(new RectF(-2*w/5,-2*h/5,2*w/5,2*h/5),r,r,paint);
        canvas.save();
        canvas.translate(0,2*w/5);
        Path path = new Path();
        path.moveTo(0,0);
        path.lineTo(w/20,w/20);
        path.lineTo(-w/20,w/20);
        path.lineTo(0,0);
        canvas.drawPath(path,paint);
        canvas.restore();
        play.draw(canvas);
        indicator.draw(canvas);
        canvas.restore();
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            animationHandler.start();
        }
        return true;
    }
    public void update(float factor) {
        play.update(factor);
        indicator.update(factor);
        postInvalidate();
    }
    private class Play {
        float wx = 0;
        public void draw(Canvas canvas) {
            Path path = new Path();
            path.moveTo(-w/20,-w/20);
            path.lineTo(w/20,0);
            path.lineTo(-w/20,w/20);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.GRAY);
            canvas.drawPath(path,paint);
            canvas.clipPath(path);
            paint.setStyle(Paint.Style.FILL);
            int r = Color.red(color),g = Color.green(color),b = Color.blue(color);
            paint.setColor(Color.argb(150,r,g,b));
            canvas.drawRect(new RectF(-w/20,-w/20,-w/20+wx,w/20),paint);
        }
        public void update(float factor) {
            wx = (w/10)*factor;
        }
    }
    private class Indicator {
        private float lx = 0;
        public void update(float factor) {
            lx = (w/20)*factor;
        }
        public void draw(Canvas canvas) {
            paint.setColor(Color.RED);
            canvas.save();
            canvas.translate(w/2,h/2+w/10);
            canvas.drawLine(-lx,0,0,0,paint);
            canvas.drawLine(0,0,lx,0,paint);
            canvas.restore();
        }
    }
    private class AnimationHandler extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener{
        private ValueAnimator startAnimator = ValueAnimator.ofFloat(0,1),endAnimator = ValueAnimator.ofFloat(1,0);
        private int dir = 0;
        private boolean isAnimated = false;
        public AnimationHandler() {
            startAnimator.setDuration(500);
            endAnimator.setDuration(500);
            startAnimator.addUpdateListener(this);
            endAnimator.addUpdateListener(this);
            startAnimator.addListener(this);
            endAnimator.addListener(this);
        }
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            update((float)valueAnimator.getAnimatedValue());
        }
        public void onAnimationEnd(Animator animator) {
            if(isAnimated) {
                dir = dir == 0?1:0;
                isAnimated = false;
            }
        }
        public void start() {
            if(!isAnimated) {
                if (dir == 0) {
                     startAnimator.start();
                } else {
                    endAnimator.start();
                }
                isAnimated = true;
            }
        }

    }
}
