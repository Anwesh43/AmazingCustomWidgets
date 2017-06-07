package com.anwesome.ui.plusminuslineview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

/**
 * Created by anweshmishra on 08/06/17.
 */

public class PlusMinusLineView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int time = 0,w,h;
    private AnimationHandler animationHandler;
    private PMLine pmLine;
    private PlusMinusButton plusMinusButton;
    public PlusMinusLineView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            animationHandler = new AnimationHandler();
            pmLine = new PMLine();
            plusMinusButton = new PlusMinusButton();
        }
        paint.setStrokeCap(Paint.Cap.ROUND);
        plusMinusButton.draw(canvas);
        pmLine.draw(canvas);
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN && animationHandler != null) {
            animationHandler.start();
        }
        return true;
    }
    public void update(float factor) {
        plusMinusButton.update(factor);
        pmLine.update(factor);
        postInvalidate();
    }
    private class PlusMinusButton {
        private float x,y,r,deg = 90;
        public PlusMinusButton() {
            x = w/2;
            y = h/2;
            r = w/10;
        }
        public void draw(Canvas canvas) {

            paint.setColor(Color.BLUE);
            canvas.save();
            canvas.translate(x, y);
            canvas.drawCircle(0,0,r,paint);
            for(int i=0;i<2;i++) {
                canvas.rotate(deg*i);
                paint.setColor(Color.WHITE);
                paint.setStrokeWidth(r/50);
                canvas.drawLine(-r/2,0,r/2,0,paint);
                canvas.restore();
            }
            canvas.restore();
        }
        public void update(float factor) {
            deg = 90*(1-factor);
        }
    }
    private class PMLine {
        float wx = 0;
        public void draw(Canvas canvas) {
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(w/80);
            canvas.save();
            canvas.translate(w/2,h/2);
            canvas.drawLine(-(wx),0,0,0,paint);
            canvas.drawLine(0,0,wx,0,paint);
            canvas.restore();
        }
        public void update(float factor) {
            wx = (3*w/10)*factor;
        }
    }
    private class AnimationHandler extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener{
        private ValueAnimator startAnim = ValueAnimator.ofFloat(0,1),endAnim = ValueAnimator.ofFloat(1,0);
        private int dir = 0;
        private boolean isAnimated = false;
        public AnimationHandler() {
            startAnim.setDuration(500);
            endAnim.setDuration(500);
            startAnim.addUpdateListener(this);
            startAnim.addListener(this);
            endAnim.addListener(this);
            endAnim.addUpdateListener(this);
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
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float factor = (float)valueAnimator.getAnimatedValue();
            update(factor);
        }
        public void onAnimationEnd(Animator animator) {
            if(isAnimated) {
                dir = dir == 0?1:0;
                isAnimated = true;
            }
        }
    }
    public static void create(Activity activity) {
        Point size = DimensionsUtil.getDeviceDimension(activity);
        int w = size.x,h = size.y;
        PlusMinusLineView plusMinusLineView = new PlusMinusLineView(activity);
        activity.addContentView(plusMinusLineView,new ViewGroup.LayoutParams(w,w));
    }
}

