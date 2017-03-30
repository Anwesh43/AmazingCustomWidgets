package com.anwesome.ui.androidbuttonloader;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 30/03/17.
 */
public class AndroidButton {
    private float x,y,size,deg = 0;
    private AndroidButton(float x,float y,float size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }
    public void draw(Canvas canvas, Paint paint) {
        AblDrawingUtil.drawAndroidShape(canvas,paint,size);
    }
    public void update() {
        deg+=20;
    }
    public int hashCode() {
        return (int)(x+y+deg);
    }
}
