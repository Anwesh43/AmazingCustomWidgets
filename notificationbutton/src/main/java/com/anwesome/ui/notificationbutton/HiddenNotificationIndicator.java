package com.anwesome.ui.notificationbutton;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by anweshmishra on 09/04/17.
 */
public class HiddenNotificationIndicator {
    private float x,y,size,n,scale = 0,dir = 0;
    public HiddenNotificationIndicator(float x,float y,float size,int n) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.n = n;
    }
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.RED);
        canvas.save();
        canvas.translate(x,y);
        canvas.scale(1,scale);
        canvas.drawRoundRect(new RectF(-size/2,-size/2,size/2,size/2),size/10,size/10,paint);
        paint.setTextSize(size/2);
        canvas.drawText(""+n,-paint.measureText(""+n)/2,paint.getTextSize()/2,paint);
        canvas.restore();
    }
    public void update() {
        scale+=dir*0.1f;
        if(scale>=1 || scale<=0) {
            dir = 0;
        }
    }
    public boolean stop() {
        return  dir == 0;
    }
    public void startMoving() {
        dir = dir == 0?1:-1;
    }
}
