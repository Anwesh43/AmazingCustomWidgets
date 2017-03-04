package com.anwesome.games.clockswitch;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

/**
 * Created by anweshmishra on 05/03/17.
 */
public class ClockSwitchButton {
    private float x,y,r,startDeg = 120,deg = 0,sweepDeg = 270,dir = 0;
    private ClockSwitchButton() {

    }
    public void select() {
        dir = 1;
    }
    public void unselect() {
        dir = -1;
    }
    public void setDimensions(float x,float y,float r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }
    public static ClockSwitchButton newInstance() {
        return new ClockSwitchButton();
    }
    public void draw(Canvas canvas,Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(r/12);
        canvas.drawArc(new RectF(-r,-r,r,r),startDeg,sweepDeg,true,paint);
        paint.setColor(Color.parseColor("#2196F3"));
        canvas.drawArc(new RectF(-r,-r,r,r),startDeg,deg,true,paint);
        canvas.save();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        canvas.rotate(startDeg+deg);
        canvas.drawCircle(0,0,r/6,paint);
        Path path = new Path();
        path.moveTo(r/6,-r/6);
        path.lineTo(r/6,r/6);
        path.lineTo(r,0);
        path.lineTo(r/6,-r/6);
        canvas.drawPath(path,paint);
        canvas.restore();
        canvas.restore();
    }
    public boolean stopped() {
        return dir == 0;
    }
    public int hashCode() {
        return (int)deg+(int)x;
    }
    public void update() {
        deg+=dir*(sweepDeg/10);
        if(deg>=sweepDeg ||  deg<=0) {
            dir = 0;
        }
    }
    public boolean handleTap(float x,float y) {
        return x>=this.x-r && x<=this.x+r && y>=this.y-r && y<=this.y+r;
    }
}
