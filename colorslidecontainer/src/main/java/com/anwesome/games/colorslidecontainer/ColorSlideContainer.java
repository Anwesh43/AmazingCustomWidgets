package com.anwesome.games.colorslidecontainer;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 21/07/17.
 */

public class ColorSlideContainer extends View{
    private int colors[];
    private int w,h,time = 0;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private AnimationHandler animationHandler = new AnimationHandler();
    private Screen mainScreen = new Screen(),minorScreen = new Screen();
    private ScreenIndicator screenIndicator = new ScreenIndicator();
    public ColorSlideContainer(Context context,int colors[]) {
        super(context);
        this.colors = colors;
        initColorSlides();
    }
    public void initColorSlides() {
        int i = 0;
        for(int color:colors) {
            ColorSlide colorSlide = new ColorSlide(color);
            mainScreen.addColorSlide(colorSlide);
            if(i!=0) {
                minorScreen.addColorSlide(colorSlide);
            }
            i++;
        }
    }
    public void update(float factor) {
        screenIndicator.update(factor);
        postInvalidate();
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            animationHandler.start();
        }
        mainScreen.draw(canvas);
        canvas.save();
        canvas.translate(0.8f*w,0.8f*h);
        canvas.scale(0.2f,0.2f);
        minorScreen.draw(canvas);
        canvas.restore();
        canvas.save();
        canvas.translate(0.9f*w, 0.9f*h);
        screenIndicator.draw(canvas);
        canvas.restore();
        time++;
    }
    private class ColorSlide {
        private int color;
        public ColorSlide(int color) {
            this.color = color;
        }
        public void draw(Canvas canvas) {
            paint.setColor(color);
            canvas.drawRect(new RectF(0,0,w,h),paint);
        }
        public int hashCode() {
            return color;
        }
    }
    private class Screen {
        private ConcurrentLinkedQueue<ColorSlide> colorSlides = new ConcurrentLinkedQueue<>();
        private int i = 0;
        public void addColorSlide(ColorSlide colorSlide) {
            colorSlides.add(colorSlide);
        }
        public void draw(Canvas canvas) {
            int index = 0;
            canvas.drawRect(new RectF(0,0,w,h),paint);
            for(ColorSlide colorSlide:colorSlides) {
                if(index < i) {
                    index++;
                    continue;
                }
                colorSlide.draw(canvas);
                break;
            }
        }
        public void incrementIndex() {
            if(i < colorSlides.size()) {
                i++;
            }
        }
        public boolean stopped() {
            return i == colorSlides.size();
        }
    }
    private class ScreenIndicator {
        private float deg = 0;
        public void draw(Canvas canvas) {
            int color = Color.parseColor("#6b6b6b");
            int r = Color.red(color),g = Color.green(color),b = Color.blue(color);
            paint.setColor(Color.argb(175,r,g,b));
            float radius = Math.min(w,h)*((0.1f)/3);
            canvas.drawArc(new RectF(-radius,-radius,radius,radius),deg,360-deg,true,paint);
        }
        public void update(float factor) {
            deg = 360*factor;
        }
        public void reset() {
            deg = 0;
        }
    }
    private class AnimationHandler extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener{
        private ValueAnimator startAnim;
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float factor = valueAnimator.getAnimatedFraction();
            update(factor);
        }
        public void onAnimationEnd(Animator animator) {
            Log.d("staus","ended");
            mainScreen.incrementIndex();
            minorScreen.incrementIndex();
            if(!minorScreen.stopped()) {
                screenIndicator.reset();
                start();
            }
        }
        public void start() {
            startAnim = ValueAnimator.ofFloat(0,1);
            startAnim.addUpdateListener(this);
            startAnim.addListener(this);
            startAnim.setDuration(3000);
            startAnim.start();
        }
    }
    public static void create(Activity activity,int[] colors) {
        ColorSlideContainer colorSlideContainer = new ColorSlideContainer(activity,colors);
        activity.setContentView(colorSlideContainer);
        if(activity instanceof AppCompatActivity) {
            ActionBar actionBar = ((AppCompatActivity)activity).getSupportActionBar();
            actionBar.hide();
        }
        else {
            android.app.ActionBar actionBar = activity.getActionBar();
            actionBar.hide();
        }
    }
}
