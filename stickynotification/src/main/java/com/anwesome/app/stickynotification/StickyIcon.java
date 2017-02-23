package com.anwesome.app.stickynotification;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 23/02/17.
 */
public class StickyIcon {
    private float x,y,finalY,initialY,dir = -1,speed = 0,scale=0;
    private Bitmap bitmap;
    private boolean stop = false;
    public boolean isStop() {
        return stop;
    }
    public void startMoving() {
        stop = false;
    }
    private StickyIcon(Bitmap bitmap,float x, float y, float finalY) {
        this.x = x;
        this.y = y;
        this.bitmap = bitmap;
        this.finalY = finalY;
        this.initialY = y;
        this.speed = Math.abs(this.finalY-this.initialY)/5;
    }
    public static  StickyIcon newInstance(Bitmap bitmap,float x,float y,float finalY) {
        return new StickyIcon(bitmap,x,y,finalY);
    }
    public void draw(Canvas canvas, Paint paint) {
        int w = bitmap.getWidth(),h = bitmap.getHeight();
        canvas.save();
        canvas.translate(x+w/2,y+h/2);
        canvas.scale(scale,scale);
        canvas.drawBitmap(bitmap,-w/2,-h/2,paint);
        canvas.restore();
    }
    public void update() {
        y+=speed*dir;
        scale-=0.2f*dir;
        if(y<=finalY) {
            dir = 1;
            stop = true;
            y = finalY;
            scale = 1;
        }
        if(y>=initialY) {
            dir = -1;
            stop = true;
            scale = 0;
            y = initialY;
        }
    }
}
