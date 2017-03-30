package com.anwesome.ui.androidbuttonloader;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 30/03/17.
 */
public class AndroidButton {
    private float x,y,size,deg = 0,dir = 1;
    public AndroidButton(float x,float y,float size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }
    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(deg);
        AblDrawingUtil.drawAndroidShape(canvas,paint,size);
        canvas.restore();
    }
    public void update() {
        deg+=20*dir;
    }
    public int hashCode() {
        return (int)(x+y+deg);
    }
    public boolean handleTap(float x,float y) {
        boolean condition = x>=this.x-size/2 && x<=this.x+size/2 && y>=this.y-size/2 && y<=this.y+size/2;
        if(condition) {
            dir = dir==0?1:0;
        }
        return condition;
    }
}
