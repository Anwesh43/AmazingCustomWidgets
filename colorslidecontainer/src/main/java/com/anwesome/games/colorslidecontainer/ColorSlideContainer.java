package com.anwesome.games.colorslidecontainer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 21/07/17.
 */

public class ColorSlideContainer extends View{
    private int colors[];
    private int w,h,time = 0;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public ColorSlideContainer(Context context,int colors[]) {
        super(context);
        this.colors = colors;
        initColorSlides();
    }
    public void initColorSlides() {

    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
        }
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
        public void draw(Canvas canvas,Paint paint) {
            int index = 0;
            canvas.drawColor(Color.WHITE);
            for(ColorSlide colorSlide:colorSlides) {
                if(index < i) {
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
            int color = Color.parseColor("#9E9E9E");
            int r = Color.red(color),g = Color.green(color),b = Color.blue(color);
            paint.setColor(Color.argb(150,r,g,b));
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
}
