package com.anwesome.app.leaninputcontainer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 27/02/17.
 */
public class LeanEditTextView {
    private float x,y,size;
    private String text = "";
    public LeanEditTextView(float x,float y,float size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.BLACK);
        paint.setTextSize(size/8);
        paint.setStrokeWidth(size/25);
        canvas.drawText(text,x-paint.measureText(text)/2,y+size/6-paint.getTextSize()/2,paint);
        paint.setColor(Color.parseColor("#FF6F00"));
        canvas.drawLine(x-size/2,y+size/6,x+size/2,y+size/6,paint);
    }
    public void addText(char letter) {
        text+=letter;
    }
    public String getText() {
        return text;
    }
}
