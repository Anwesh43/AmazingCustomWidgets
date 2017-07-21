package com.anwesome.games.colorslidecontainer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

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
}
