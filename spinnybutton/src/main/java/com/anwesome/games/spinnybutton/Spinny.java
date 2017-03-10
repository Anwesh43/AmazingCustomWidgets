package com.anwesome.games.spinnybutton;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by anweshmishra on 11/03/17.
 */
public class Spinny {
    private float x,y,radius,deg = 0,distY=0,minR,dir =0;
    private Spinny(float w,float h) {
        this.x = w/2;
        this.y = h/2;
        this.radius = w/3;
        this.minR = w/6;
    }
    public static Spinny getInstance(float w,float h) {
        return new Spinny(w,h);
    }
    public void draw(Canvas canvas, Paint paint) {
        int dir[] = {1,-1};
        canvas.save();
        canvas.translate(x,y);
        canvas.save();
        canvas.translate(0,distY);
        canvas.rotate(deg);
        for(int i=0;i<2;i++) {
            canvas.save();
            canvas.scale(dir[i],1);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawArc(new RectF(-radius, -radius, radius, radius), -90, 180, true, paint);
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(20);
            canvas.drawArc(new RectF(-minR, -minR, minR, minR), -30, 60, false, paint);
            canvas.restore();
        }
        canvas.restore();
        canvas.restore();
    }
    public void update() {
        if ((dir == 1 && deg < 90) || (dir == -1 && deg > 0)) {
            deg+=dir*15;
        }
        else if((dir == 1 && distY<minR) || (dir == -1 && distY>0)){
            if((dir == 1) && distY>=minR) {
                distY  = minR;
                dir = 0;
            }
            else if((dir == -1 && distY<=0)) {
                distY = 0;
                dir = 0;
            }
        }
    }
    public boolean stopped() {
        return dir == 0;
    }
    public void startMoving() {
        dir = (deg == 0)?1:-1;
    }
    public int hashCode() {
        return (int)(x+y+dir+deg+distY);
    }
}
