package com.anwesome.games.zoomshapebutton;

import android.graphics.*;

/**
 * Created by anweshmishra on 13/03/17.
 */
public class ZoomShape {
    private float size,x,y,deg=0,l=0,dir = 0;
    public ZoomShape(float w,float h) {
        this.x = w/2;
        this.y = h/2;
        this.size = w/3;
    }
    public void draw(Canvas canvas, Paint paint) {
        paint.setStrokeWidth(20);
        paint.setColor(Color.parseColor("#BDBDBD"));
        paint.setStyle(Paint.Style.STROKE);
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(-deg);
        canvas.drawLine(0,0,0,-l,paint);
        canvas.drawCircle(0,-l-size/4,size/4,paint);
        canvas.restore();
    }
    public void update() {
        if ((dir == 1 && l < size / 2) || (dir == -1 && l > 0)) {
            l += (size / 10) * dir;
            if (l > size / 2) {
                l = size / 2;
            }
            if (l < 0) {
                l = 0;
            }
        } else {
            deg += 9 * dir;
            if(deg>=45 || deg<=0) {
                dir = 0;
            }
        }

    }

    public boolean stopped() {
        return dir == 0;
    }
    public int hashCode() {
        return (int)(x+y+deg+l);
    }
    public void handleTap() {
        if(dir == 0) {
            dir = (deg == 0) ? 1 : -1;
        }
    }
}
