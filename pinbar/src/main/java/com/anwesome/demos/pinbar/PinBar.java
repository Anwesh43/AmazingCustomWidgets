package com.anwesome.demos.pinbar;

import android.graphics.*;

/**
 * Created by anweshmishra on 29/03/17.
 */
public class PinBar {
    private float x,y,w,h1,h2,hSpeed,deg = 0,dir = 0,n = 3;
    private PinBar(float x,float y,float w,float h,float n) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h1 = h/2;
        this.h2 = h;
        this.hSpeed = h/10;
        this.n = Math.max(n,this.n);
    }
    public static PinBar getInstance(float x,float y,float w,float h,float n) {
        return new PinBar(x,y,w,h,n);
    }
    public void update() {
        deg+=dir*10;
        h1+=dir*hSpeed;
        h2-=dir*hSpeed;
        if(deg<=0 || deg>=90 ) {
            if(dir == 1) {
                h1 = 10*hSpeed;
                h2 = 5*hSpeed;
            }
            else {
                h1 = 5*hSpeed;
                h2 = 10*hSpeed;
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
        }
        canvas.restore();
    }
    public void drawPin(Canvas canvas,Paint paint,float gap,float h) {
        float origH = hSpeed*10;
        canvas.drawRect(-gap/2,-origH/10,gap/2,0,paint);
        canvas.drawRect(-gap/2,-origH/5,gap/2,h,paint);
    }
    public int hashCode() {
        return (int)(x+y+w+deg+dir+h1+h2);
    }
    public boolean stopped() {
        return dir == 0;
    }
}
