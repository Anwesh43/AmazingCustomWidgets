package com.anwesome.app.stickynotification;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

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
            textElement.setPivots(x+w/2,y);
        }
    }
    public void draw(Canvas canvas, Paint paint) {
        float r = Math.max(w,h)/10;
        paint.setColor(Color.parseColor("#4FC3F7"));
        canvas.save();
        canvas.translate(x+w/2,y);
        canvas.scale(1,scale);
        canvas.drawRoundRect(new RectF(-w/2,0,w/2,h),r,r,paint);
        for(StickyNotificationTextElement textElement:textElements) {
            textElement.draw(canvas,paint);
        }
        canvas.restore();
    }
    public void update() {
        scale+=scaleDir;
        if(scale>=1) {
            stop = true;
            scaleDir = 0;
        }
        if(scale<=0) {
            stop = true;
            scaleDir = 0;
        }
    }
}
