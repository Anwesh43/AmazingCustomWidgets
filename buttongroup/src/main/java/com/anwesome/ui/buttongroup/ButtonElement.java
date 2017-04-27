package com.anwesome.ui.buttongroup;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 27/04/17.
 */
public class ButtonElement {
    private int color;
    private String title = "";
    private float x,y,w,h;
    public ButtonElement(String title,int color) {
        this.title = title;
        this.color = color;
    }
    public void setDimension(float x,float y,float w,float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    public void draw(Canvas canvas, Paint paint) {

    }
    public int hashCode() {
        return title.hashCode()+(int)h;
    }
}
