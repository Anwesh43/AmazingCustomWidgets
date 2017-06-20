package com.anwesome.ui.colorbarstack;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 20/06/17.
 */

public class ColorBarStack {
    public static class ColorBarStackView extends View {
        private ColorBarStackContainer colorBarStackContainer;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private int w,h,time=0;
        private Bitmap bitmap;
        private int colors[];
        public ColorBarStackView(Context context,Bitmap bitmap,int[] colors) {
            super(context);
            this.bitmap = bitmap;
            this.colors = colors;
        }
        public void onDraw(Canvas canvas) {
            if(time == 0) {
                w = canvas.getWidth();
                h = canvas.getHeight();
                bitmap = Bitmap.createScaledBitmap(bitmap,w/2,w/2,true);
                colorBarStackContainer = new ColorBarStackContainer(this,colors,w/2,w/2);
            }
            canvas.drawBitmap(bitmap,w/4,h/2-w/4,paint);
            if(colorBarStackContainer != null) {
                colorBarStackContainer.draw(canvas,paint);
            }
            time++;
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && colorBarStackContainer != null) {
                colorBarStackContainer.handleTap();
            }
            return true;
        }
        public void update(float factor) {
            postInvalidate();
        }
    }
    private static class ColorBar {
        private int color;
        private float dir = 0,wBar = 0,y,w,h;
        public ColorBar(int color,float y,float w,float h) {
            this.color = color;
            this.y = y;
            this.w = w;
            this.h = h;
        }
        public void draw(Canvas canvas,Paint paint) {
            paint.setColor(Color.BLACK);
            canvas.save();
            canvas.translate(0,y);
            int r = Color.red(color),g = Color.green(color),b = Color.blue(color);
            paint.setColor(Color.argb(150,r,g,b));
            canvas.drawRect(0,0,wBar,h,paint);
            canvas.restore();
        }
        public void update(float factor) {
            wBar = w/2*factor;
        }
        public int hashCode() {
            return (int)(y+wBar)+color;
        }
    }
    private static class ColorBarStackContainer {
        private ColorBarStackView view;
        private AnimationHandler animationHandler;
        private float dir = 0;
        private int index = 0;
        private ConcurrentLinkedQueue<ColorBar> colorBars = new ConcurrentLinkedQueue<>();
        public ColorBarStackContainer(ColorBarStackView view,int colors[],float w,float h) {
            this.view = view;
            if(colors.length > 0) {
                initColorBars(colors,w,h);
            }
            animationHandler = new AnimationHandler(this);
        }
        private void initColorBars(int colors[],float w,float h) {
            float yGap = h/colors.length,y = h-yGap;
            for(int color:colors) {
                colorBars.add(new ColorBar(color,y,w,yGap));
            }
        }
        public void draw(Canvas canvas,Paint paint) {
            int i=0;
            for(ColorBar colorBar:colorBars) {
                colorBar.draw(canvas,paint);
                i++;
                if(i == index) {
                    break;
                }
            }
        }
        public void update(float factor) {
            int i = 0;
            ColorBar currBar = null;
            for(ColorBar colorBar:colorBars) {
                if(i == index) {
                    currBar = colorBar;
                    break;
                }
                i++;
            }
        }
        public void adjustParametersOnAnimEnd() {
            index+=dir;
            if(index == colorBars.size()) {
                dir = -1;
                index += dir;
            }
            if(index < 0) {
                index = 0;
                dir = 1;
            }
        }
        public void handleTap() {
            if(dir == 1){
                animationHandler.open();
            }
            else {
                animationHandler.close();
            }
        }
    }
    private static class AnimationHandler extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener{
        private ValueAnimator openAnim = ValueAnimator.ofFloat(0,1),closeAnim = ValueAnimator.ofFloat(1,0);
        private boolean isAnimated = false;
        private ColorBarStackContainer colorBarStackContainer;
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (colorBarStackContainer != null && isAnimated) {
                colorBarStackContainer.update((float)valueAnimator.getAnimatedValue());
            }
        }
        public void onAnimationEnd(Animator animator) {
            if(colorBarStackContainer != null && isAnimated) {
                colorBarStackContainer.adjustParametersOnAnimEnd();
                isAnimated = true;
            }
        }
        public void open() {
            if(!isAnimated) {
                openAnim.start();
                isAnimated = true;
            }
        }
        public void close() {
            if(!isAnimated) {
                closeAnim.start();
                isAnimated = true;
            }
        }
        public AnimationHandler(ColorBarStackContainer colorBarStackContainer) {
            openAnim.setDuration(500);
            closeAnim.setDuration(500);
            openAnim.addListener(this);
            closeAnim.addListener(this);
            openAnim.addUpdateListener(this);
            closeAnim.addUpdateListener(this);
            this.colorBarStackContainer = colorBarStackContainer;
        }
    }
    public static void create(Activity activity,Bitmap bitmap,int colors[]) {
        ColorBarStackView colorBarStackView = new ColorBarStackView(activity,bitmap,colors);
        Point size = DimensionsUtil.getDeviceDimension(activity);
        activity.addContentView(colorBarStackView,new ViewGroup.LayoutParams(size.x,size.x));
    }
}
