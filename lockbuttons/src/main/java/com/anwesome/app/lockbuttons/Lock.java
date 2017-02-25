package com.anwesome.app.lockbuttons;

import android.graphics.*;

/**
 * Created by anweshmishra on 25/02/17.
 */
public class Lock {
    private float x,y,deg = 0,degSpeed = 0,r;
    public Lock(float x,float y,float r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }
    public void open() {
        degSpeed = -6;
    }
    public void close() {
        degSpeed = 6;
    }
    public void update() {
        deg+=degSpeed;
        if(deg<=-30) {
            degSpeed = 0;
        }
        if(deg>=0) {
            degSpeed = 0;
        }
    }
    public boolean isStop() {
        return degSpeed == 0;
    }
    public void draw(Canvas canvas,Paint paint) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#4DD0E1"));
        paint.setStrokeWidth(canvas.getWidth()/25);
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(deg);
        canvas.drawArc(new RectF(0,-r,2*r,r),180,180,false,paint);
        canvas.restore();
    }
}
