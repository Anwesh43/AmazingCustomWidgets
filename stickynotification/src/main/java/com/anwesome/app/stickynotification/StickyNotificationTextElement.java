package com.anwesome.app.stickynotification;

import android.graphics.*;
/**
 * Created by anweshmishra on 23/02/17.
 */
public class StickyNotificationTextElement {
    private float x,y,pivotX = 0,pivotY;
    private String text;
    public StickyNotificationTextElement(String text,float x,float y) {
        this.text = text;
        this.x = x;
        this.y = y;
    }
    public void setPivots(float x,float y) {
        this.pivotX = x;
        this.pivotY = y;
    }
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.WHITE);
        canvas.drawText(text,x-pivotX,y-pivotY,paint);
    }
    public int hashCode() {
        return (int)y+text.hashCode();
    }
}
