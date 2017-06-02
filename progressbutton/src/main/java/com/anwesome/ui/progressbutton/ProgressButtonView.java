package com.anwesome.ui.progressbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 03/06/17.
 */

public class ProgressButtonView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int time = 0,w,h;
    private boolean isAnimating = false;
    private int touchedButton = -1;
    private List<ProgressBar> progressBars = new ArrayList<>();
    public ProgressButtonView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            for(int i=0;i<2;i++) {
                progressBars.add(new ProgressBar(i));
            }
        }
        paint.setColor(Color.parseColor("#283593"));
        for(ProgressBar progressBar:progressBars) {
            progressBar.draw(canvas);
        }
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            for(ProgressBar progressBar:progressBars) {
                progressBar.handleTap(event.getX(),event.getY());
                if(touchedButton != -1) {
                    break;
                }
            }
        }
        return true;
    }
    public void update(float factor) {
        if(touchedButton != -1) {
            progressBars.get(touchedButton).update(factor);
            postInvalidate();
        }
    }
    private class CircularProgressBar {
        private float x,y,r,deg = 0;
        private int i;
        public CircularProgressBar(int i) {
            x = w/4+(w/2)*i;
            y = h/2;
            r = w/10;
            this.i = i;
        }
        public void draw(Canvas canvas) {
            canvas.save();
            canvas.translate(x,y);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(w/40);
            canvas.drawCircle(0,0,r,paint);
            paint.setStyle(Paint.Style.FILL);
            Path path = new Path();
            path.moveTo(0,0);
            for(int i=0;i<=deg;i++) {
                float x = (float)(r*Math.cos(i*Math.PI/180)),y = (float)(r*Math.sin(i*Math.PI/180));
                path.lineTo(x,y);
            }
            canvas.drawPath(path,paint);
            canvas.restore();
        }
        public void update(float factor) {
            deg = 360*factor;
        }
        public int hashCode() {
            return (int)(x+deg);
        }
        public boolean handleTap(float x,float y) {
            boolean condition = x>=this.x-r && x<=this.x+r && y>=this.y-r && y<=this.y+r;
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
                touchedButton = -1;
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
    private class ProgressBar {
        private CircularProgressBar circularProgressBar;
        private LinearBar linearBar;
        private int i;
        private AnimationHandler animationHandler;
        public ProgressBar(int i) {
            circularProgressBar = new CircularProgressBar(i);
            linearBar = new LinearBar(i);
            animationHandler = new AnimationHandler();
            this.i = i;
        }
        public void draw(Canvas canvas) {
            circularProgressBar.draw(canvas);
            linearBar.draw(canvas);
        }
        public void update(float factor) {
            circularProgressBar.update(factor);
            linearBar.update(factor);
        }
        public boolean handleTap(float x,float y) {
            boolean condition =  circularProgressBar.handleTap(x,y);
            if(condition) {
                animationHandler.start();
                touchedButton = i;
            }
            return condition;
        }
        public int hashCode() {
            return linearBar.hashCode()+circularProgressBar.hashCode();
        }
    }
    public static void create(Activity activity) {
        Point size = DimensionsUtil.getDeviceDimension(activity);
        int mainW = size.x,mainH = size.y;
        ProgressButtonView progressButtonView = new ProgressButtonView(activity);
        activity.addContentView(progressButtonView,new ViewGroup.LayoutParams(mainW,mainH));
    }
}
