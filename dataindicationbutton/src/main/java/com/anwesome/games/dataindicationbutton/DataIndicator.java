package com.anwesome.games.dataindicationbutton;

import android.graphics.*;

/**
 * Created by anweshmishra on 21/03/17.
 */
public class DataIndicator {
    private DataIndicationType dataIndicationType;
    private float x,y,radius,deg = 0,dir = 0;
    public DataIndicator(DataIndicationType dataIndicationType,float x,float y,float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.dataIndicationType = dataIndicationType;
    }
    public void draw(Canvas canvas,Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(deg);
        if(deg == 90) {
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(0,0,radius,paint);
            paint.setColor(Color.BLACK);
        }
        else {
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.WHITE);
            canvas.drawCircle(0,0,radius,paint);
        }
        paint.setStyle(Paint.Style.STROKE);
        switch(dataIndicationType) {
            case NETWORK_DATA:
                drawNetworkData(canvas,paint);
                break;
            case WIFI:
                drawWifi(canvas,paint);
        }
        canvas.restore();
    }
    public void startMoving() {
        if(deg == 0) {
            dir = 1;
        }
        else {
            dir = -1;
        }
    }
    public void update() {
        deg+=10*dir;
        if(deg>=90 || dir<=0) {
            dir = 0;
        }
    }
    public boolean stopped() {
        return dir == 0;
    }
    private void drawNetworkData(Canvas canvas,Paint paint) {
        for(int i=0;i<2;i++) {
            float lineDir = i*2-1;
            canvas.save();
            canvas.scale(lineDir,lineDir);
            canvas.drawLine(radius/2,-(2*radius)/3,radius/2,(2*radius)/3,paint);
            canvas.drawLine(radius/2,(2*radius)/3,radius/2+radius/8,(2*radius)/3-radius/8,paint);
            canvas.restore();
        }
    }
    private void drawWifi(Canvas canvas,Paint paint) {
        float r = radius/60;
        canvas.drawCircle(0,0,r,paint);
        for(int i=1;i<=3;i++) {
            float newR = r*(i*10);
            canvas.drawArc(new RectF(-r,-r,r,r),240,60,false,paint);
        }
    }
    public int hashCode() {
        return (int)(x+y+deg+dir);
    }
}
