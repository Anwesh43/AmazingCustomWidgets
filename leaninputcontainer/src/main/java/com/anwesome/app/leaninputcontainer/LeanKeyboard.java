package com.anwesome.app.leaninputcontainer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by anweshmishra on 27/02/17.
 */
public class LeanKeyboard {

    private char currChar;
    private float x,y,w,h;
    private LeanKeyboard(float x,float y,float w,float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        initDimensions();
    }
    public static LeanKeyboard newInstance(float x,float y,float w,float h) {
        return new LeanKeyboard(x,y,w,h);
    }
    public void initDimensions() {

    }
    private class LeanKey {
        private char letter;
        private float x,y,size,scale = 0,scaleDir = 0;
        public void startScalingUp() {
            scaleDir = 0.1f;
        }
        public void startScalingDown() {
            scaleDir = -0.1f;
        }
        public void draw(Canvas canvas, Paint paint) {
            paint.setColor(Color.BLACK);
            paint.setTextSize(size/2);
            canvas.drawText(""+letter,x,y,paint);
            paint.setColor(Color.parseColor("#AABDBDBD"));
            canvas.save();
            canvas.translate(x,y);
            canvas.scale(scale,scale);
            canvas.drawCircle(0,0,size/2,paint);
            canvas.restore();
        }
        public void update() {
            scale+=scaleDir;
            if(scale>=1) {
                scaleDir*=1;
            }
            if(scale<=0) {
                scaleDir  = 0;
            }
        }
        public boolean handleTap(float x,float y) {
            return x>=this.x-size/2 && x<=this.x+size/2 && y>=this.y-size/2 && y<=this.y+size/2;
        }
        public boolean isStop() {
            return scaleDir == 0;
        }
        public int hashCode() {
            return (int)x+(int)y+(int)scaleDir+(int)scale+letter;
        }
    }
}
