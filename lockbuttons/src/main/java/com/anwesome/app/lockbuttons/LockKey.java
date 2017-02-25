package com.anwesome.app.lockbuttons;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by anweshmishra on 25/02/17.
 */
public class LockKey {
    private float pivotX,pivotY,deg = 0,degSpeed = 0;
    public LockKey(float pivotX,float pivotY) {
        this.pivotX = pivotX;
        this.pivotY = pivotY;
    }
    public boolean isStopped() {
        return degSpeed == 0;
    }
    public void open() {
        degSpeed = 9;
    }
    public void close() {
        degSpeed = -9;
    }
    public void update() {
        deg+=degSpeed;
        if(deg>=45) {
            degSpeed = 0;
        }
        if(deg<=0) {
            degSpeed = 0;
        }
    }
    public void draw(Canvas canvas, Paint paint) {
        int scale[] = {1,-1};
        int w = canvas.getWidth(),h = canvas.getHeight(),r1=w/20,r2=w/12,r3=w/20;
        canvas.save();
        canvas.translate(pivotX,pivotY);
        canvas.rotate(deg);
        for(int i=0;i<2;i++) {
            canvas.save();
            canvas.scale(1,scale[i]);
            Path path = new Path();
            path.moveTo(-r1,0);
            path.lineTo(-r3/2,-r2);
            path.lineTo(r3/2,-r2);
            path.lineTo(r1,0);
            canvas.drawPath(path,paint);
            canvas.restore();
        }
        canvas.restore();
    }
    public int hashCode() {
        return (int)pivotX+(int)pivotY+(int)deg+(int)degSpeed;
    }
}
