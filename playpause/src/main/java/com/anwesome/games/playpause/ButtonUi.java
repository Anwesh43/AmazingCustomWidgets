package com.anwesome.games.playpause;

import android.graphics.*;

/**
 * Created by anweshmishra on 15/03/17.
 */
public class ButtonUi {
    private float x,y,w,h,dir=0,deg=0,scale=0;
    public void setDimensions(float x,float y,float w,float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.scale = getInitialScale();
    }
    public void startMoving(int dir) {
        this.dir = dir;
    }
    protected float getInitialScale() {
        return scale;
    }
    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(deg);
        canvas.scale(scale,scale);
        drawButton(canvas,paint,w,h);
        canvas.restore();
    }
    protected void drawButton(Canvas canvas,Paint paint,float w,float h) {

    }
    public boolean stopped() {
        return dir == 0;
    }
    public void update() {
        deg+=72*dir;
        scale+=0.2f*dir;
        if((scale>=1 && deg>=360) || (scale <=0 && deg<=0)) {
            dir = 0;
            if(scale>=1) {
                deg = 360;
                scale = 1;
            }
            if(scale<=0) {
                scale = 0;
                deg = 0;
            }
        }
    }
    public int hashCode() {
        return (int)(deg+scale+x+y+w+h);
    }
}
