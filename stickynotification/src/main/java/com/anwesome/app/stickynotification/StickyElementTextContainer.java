package com.anwesome.app.stickynotification;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.*;

/**
 * Created by anweshmishra on 23/02/17.
 */
public class StickyElementTextContainer {
    private float scale = 0,scaleDir =0;
    private boolean stop = false;
    private List<StickyNotificationTextElement> textElements = new ArrayList<>();
    private float x,y,w,h;
    public boolean isStop() {
        return stop;
    }
    public void startMoving() {
        stop = false;
        if(scale <= 0) {
            scaleDir = 0.25f;
        }
        if(scale >= 1) {
            scaleDir = -0.25f;
        }
    }
    public StickyElementTextContainer(float x,float y,List<StickyNotificationTextElement> textElements,int w,int h) {
        this.x = x;
        this.y = y;
        this.textElements = textElements;
        this.w = w;
        this.h = h;
        setPivotOfText();
    }
    private void setPivotOfText() {
        for(StickyNotificationTextElement textElement:textElements) {
            textElement.setPivots(x+w/2,y+h/2);
        }
    }
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.parseColor("#4FC3F7"));
        canvas.save();
        canvas.translate(x+w/2,y+h/2);
        canvas.scale(1,scale);
        for(StickyNotificationTextElement textElement:textElements) {
            textElement.draw(canvas,paint);
        }
        canvas.restore();
    }
    public void update() {
        scale+=scaleDir;
        if(scale>=1) {
            stop = true;
        }
        if(scale<=0) {
            stop = true;
        }
    }
}
