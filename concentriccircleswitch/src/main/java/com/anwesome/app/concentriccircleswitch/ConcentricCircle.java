package com.anwesome.app.concentriccircleswitch;

import android.graphics.*;

/**
 * Created by anweshmishra on 03/03/17.
 */
public class ConcentricCircle {
    private float deg = 0,dir = 0,x=0,y=0,r=100;
    private int backgroundColor = Color.WHITE;
    private ConcentricCircle() {

    }
    public static ConcentricCircle newInstance() {
        return new ConcentricCircle();
    }
    public void setDimensions(float x,float y,float r,int backgroundColor) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.backgroundColor = backgroundColor;
    }
    public void startFilling() {
        dir = 1;
    }
    public void startRemovingFill() {
        dir = -1;
    }
    public boolean stopped() {
        return dir == 0;
    }
    public int hashCode() {
        return (int)dir+(int)deg;
    }
    public void draw(Canvas canvas,Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#673AB7"));
        canvas.drawArc(new RectF(x-r,y-r,x+r,y+r),0,deg,true,paint);
        paint.setStrokeWidth(r/30);
        paint.setColor(backgroundColor);
        canvas.drawCircle(x,y,(2*r)/3,paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GRAY);
        canvas.drawCircle(x,y,r,paint);
        paint.setColor(Color.GRAY);
        canvas.drawCircle(x,y,(2*r)/3,paint);
    }
    public void update() {
        deg+=dir*24;
        if(deg>=360) {
            deg = 360;
            dir = 0;
        }
        else if(deg<=0) {
            deg = 0;
            dir = 0;
        }
    }
    public boolean handleTap(float x,float y) {
        return x>=this.x-r && x<=this.x+r && y>=this.y-r && y<=this.y+r;
    }
}
