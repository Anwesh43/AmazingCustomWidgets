package com.anwesome.app.leaninputcontainer;

import android.graphics.*;

/**
 * Created by anweshmishra on 04/03/17.
 */
public class SubmitButton {
    private float x,y,w,h,scale=0;
    private String text;
    private SubmitButton(String text) {
        this.text = text;
    }
    private boolean stopped() {
        return scale>=1;
    }
    public static SubmitButton newInstance(String text) {
        return new SubmitButton(text);
    }
    public void setDimensions(float x,float y,float w,float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    public void draw(Canvas canvas,Paint paint) {
        float r = Math.max(w,h)/6;
        canvas.save();
        canvas.translate(x,y);
        canvas.scale(scale,scale);
        canvas.drawRoundRect(new RectF(-w/2,-h/2,w/2,h/2),r,r,paint);
        canvas.restore();
    }
    public boolean handleTap(float x,float y) {
        return x>=this.x-w/2 && x<=this.x+w/2 && y>=this.y-h/2 && y<=this.y+h/2;
    }
    public void update() {
        if(scale<1) {
            scale += 0.2f;
        }
    }
}
