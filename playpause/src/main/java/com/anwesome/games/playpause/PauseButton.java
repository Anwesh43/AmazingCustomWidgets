package com.anwesome.games.playpause;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 15/03/17.
 */
public class PauseButton {
    private float x,y,w,h,scale=0,dir = 0,deg = 0;
    public PauseButton(float x,float y,float w,float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    public void startMoving(int dir) {
        this.dir = dir;
    }
    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(deg);
        canvas.scale(scale,scale);
        canvas.drawLine(-w/2,-h/2,-w/2,h/2,paint);
        canvas.drawLine(w/2,-h/2,w/2,h/2,paint);
        canvas.restore();
    }
    public void update() {
        deg+=dir*36;
        scale+=dir*0.1f;
        if((deg>=360 && scale>=1) || (deg<=0 && scale<=0)) {
            dir = 0;
        }
    }
    public int hashCode(){
        return (int)(x+y+w+h+scale+deg);
    }
}
