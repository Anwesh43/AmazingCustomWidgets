package com.anwesome.app.stickynotification;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 23/02/17.
 */
public class CloseButton {
    private float x,y,r,deg = 0,dir = 1,rotDir = 0;
    public CloseButton(float x,float y,float r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }
    public void draw(Canvas canvas, Paint paint) {
        paint.setStrokeWidth(canvas.getWidth()/80);
        paint.setColor(Color.parseColor("#4FC3F7"));
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(deg);
        canvas.drawCircle(0,0,r,paint);
        paint.setColor(Color.WHITE);
        canvas.drawLine(-r/2,0,r/2,0,paint);
        canvas.drawLine(0,-r/2,0,r/2,paint);
        canvas.restore();
    }
    public void update() {
        deg+=rotDir*dir;
        if(deg>=45) {
            rotDir = 0;
            dir = -1;
        }
        if(deg<=0) {
            rotDir = 0;
            dir = 1;
        }
    }
    public void startMoving() {
        rotDir = 9;
    }
    public boolean handleTap(float x,float y) {
        return (x>=this.x-r && x<=this.x+r && y>=this.y-r && y<=this.y+r);
    }
    public boolean isStop() {
        return (rotDir == 0);
    }
}
