package com.anwesome.demos.pinbar;

import android.graphics.*;

/**
 * Created by anweshmishra on 29/03/17.
 */
public class PinBar {
    private float x,y,w,h1,h2,hSpeed,deg = 0,dir = 0,n = 3,origH;
    private PinBar(float x,float y,float w,float h,float n) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h1 = h/2;
        this.h2 = h;
        this.origH = h;
        this.hSpeed = h/20;
        this.n = Math.max(n,this.n);
    }
    public static PinBar getInstance(float x,float y,float w,float h,float n) {
        return new PinBar(x,y,w,h,n);
    }
    public void update() {
        deg+=dir*9;
        h1+=dir*hSpeed;
        h2-=dir*hSpeed;
        if(deg<=0 || deg>=90 ) {
            if(dir == 1) {
                h1 = origH;
                h2 = origH/2;
            }
            else {
                h1 = origH/2;
                h2 = origH;
            }
            dir = 0;
        }
    }
    public void startMoving() {
        dir = deg == 0?1:-1;

    }
    public void draw(Canvas canvas,Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(deg);
        float gap = (w)/(2*n-2),xbar = -w/2+gap,ybar=0;
        for(int i = 0;i<n;i++) {
            canvas.save();
            canvas.translate(xbar, ybar);
            float newH = h1;
            if(i%2==1) {
                newH = h2;
            }
            drawPin(canvas, paint, gap, newH);
            canvas.restore();
            xbar+=2*gap;
        }
        canvas.restore();
    }
    public void drawPin(Canvas canvas,Paint paint,float gap,float h) {
        canvas.drawRect(-gap/2,origH/10,gap/2,origH/5,paint);
        canvas.drawRect(-gap/2,-h,gap/2,0,paint);
    }
    public int hashCode() {
        return (int)(x+y+w+deg+dir+h1+h2);
    }
    public boolean stopped() {
        return dir == 0;
    }
}
