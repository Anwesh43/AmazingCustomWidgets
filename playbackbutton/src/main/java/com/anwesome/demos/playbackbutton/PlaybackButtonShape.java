package com.anwesome.demos.playbackbutton;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by anweshmishra on 27/03/17.
 */
public class PlaybackButtonShape {
    private float x,y,deg=0,dir=0,r;
    private PlaybackButtonShape(float x,float y,float r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }
    public static PlaybackButtonShape getInstance(float x,float y,float r) {
        return new PlaybackButtonShape(x,y,r);
    }
    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(deg);
        paint.setColor(Color.parseColor("#757575"));
        Path path = new Path();
        path.moveTo(0,r);
        path.lineTo(r,0);
        path.lineTo(0,-r);
        path.lineTo(0,r);
        canvas.drawPath(path,paint);
        paint.setStrokeWidth(r/10);
        canvas.drawLine(r,-r,r,r,paint);
        canvas.restore();
    }
    public void start() {
        this.dir = 1;
    }
    public void update() {
        this.deg+=dir*20;
        if(this.deg%180 == 0) {
            dir = 0;
        }
    }
    public int hashCode() {
        return (int)(x+y+r+deg);
    }
    public boolean stopped() {
        return dir == 0;
    }
}
