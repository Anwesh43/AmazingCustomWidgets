package com.anwesome.app.stickynotification;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 23/02/17.
 */
public class StickyIcon {
    private float x,y,finalY,initialY,dir = -1,speed = 0;
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
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(bitmap,x,y,paint);
    }
    public void update() {
        y+=speed*dir;
        if(y<=finalY) {
            dir = 1;
            stop = true;
        }
        if(y>=initialY) {
            dir = -1;
            stop = true;
        }
    }
}
