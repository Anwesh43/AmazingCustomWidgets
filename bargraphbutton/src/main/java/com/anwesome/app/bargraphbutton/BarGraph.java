package com.anwesome.app.bargraphbutton;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by anweshmishra on 02/03/17.
 */
public class BarGraph {
    private float x,y,w,h,scale = 0.2f,dir = 0;
    private int color = Color.parseColor("#009688");
    private BarGraph() {

    }
    public static BarGraph newInstance() {
        return new BarGraph();
    }
    public void setDimensions(float x,float y,float w,float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(color);
        canvas.save();
        canvas.translate(x+w/2,y);
        canvas.scale(scale,scale);
        canvas.drawRect(new RectF(-w/2,-h,w/2,0),paint);
        canvas.restore();
    }
    public void update() {
        scale+=0.1f*dir;
        if(scale>=1 || scale<=0.2f) {
            dir = 0;
            if(scale>=1) {
                scale = 1;
            }
            if(scale<=0.2f) {
                scale = 0.2f;
            }
        }
    }
    public boolean stopped() {
        return dir == 0;
    }
    public void setDir(float dir) {
        this.dir = dir;
    }
    public int hashCode() {
        return (int)x+(int)y;
    }
    public boolean handleTap(float x,float y) {
        boolean condition = false;
        if(!((scale >= 1)|| dir != 0)) {
            condition = x>=this.x && x<=this.x+w && y>=this.y-h*0.2f && y<=this.y;
        }
        return condition;
    }
}
