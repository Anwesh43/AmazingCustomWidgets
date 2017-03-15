package com.anwesome.games.playpause;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by anweshmishra on 15/03/17.
 */
public class PlayButton {
    private float x,y,w,h,deg=0,scale=1f,dir = 0;
    public PlayButton(float x,float y,float w,float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    public void startMoving(int dir) {
        this.dir = dir;
    }
    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(deg);
        canvas.scale(scale,scale);
        Path path = new Path();
        path.lineTo(-w/2,h/2);
        path.lineTo(w/2,h/2);
        path.lineTo(0,-h/2);
        canvas.drawPath(path,paint);
        canvas.restore();
    }
    public boolean stopped() {
        return dir == 0;
    }
    public void update() {
        deg+=36*dir;
        scale+=0.1f*dir;
        if((scale>=1 && deg>=360) || (scale <=0 || deg<=0)) {
            dir = 0;
        }
    }
}
