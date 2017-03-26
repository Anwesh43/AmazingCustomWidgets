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
    private PlaybackButtonType type;
    private PlaybackButtonShape(float x,float y,float r,PlaybackButtonType type) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.type = type;
    }
    public static PlaybackButtonShape getInstance(float x,float y,float r,PlaybackButtonType type) {
        return new PlaybackButtonShape(x,y,r,type);
    }
    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(deg);
        paint.setColor(Color.parseColor("#757575"));
        PlaybackButtonUtil.drawShape(canvas,paint,r,type);
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
