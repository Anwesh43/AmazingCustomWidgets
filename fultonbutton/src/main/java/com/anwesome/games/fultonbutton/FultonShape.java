package com.anwesome.games.fultonbutton;

import android.graphics.*;

/**
 * Created by anweshmishra on 10/03/17.
 */
public class FultonShape {
    private float rot = 90,x=0,y=0,r=100,dir = 0,cy=0;
    private FultonShape(float w,float h) {
        this.x = w/2;
        this.y = h/2;
        this.r = w/4;
    }
    public static FultonShape getInstance(float w,float h) {
        return new FultonShape(w,h);
    }
    public boolean stopped() {
        return dir == 0;
    }
    public void draw(Canvas canvas,Paint paint) {
        int l[] = {1,-1};
        paint.setColor(Color.parseColor("#4CAF50"));
        canvas.save();
        canvas.translate(x,y);
        canvas.drawCircle(0,cy,r,paint);
        paint.setColor(Color.GRAY);
        for(int i=0;i<2;i++) {
            canvas.save();
            canvas.rotate(rot);
            canvas.scale(l[i],1);
            canvas.drawPath(defineProtractor(),paint);
            canvas.restore();
        }
        paint.setColor(Color.BLACK);
        canvas.drawCircle(0,0,r/10,paint);
        canvas.restore();
    }
    private Path defineProtractor() {
        Path path = new Path();
        path.moveTo(-r/10,0);
        path.lineTo(-r/10,9*r/10);
        path.lineTo(0,r);
        path.lineTo(r/10,9*r/10);
        path.lineTo(r/10,0);
        return path;
    }
    public void handleTap() {
        if(dir == 0) {
            dir = rot == 90?1:-1;
        }
    }
    public void update() {
        rot-=9*dir;
        cy-=(r)/5;
        if(rot <= 45 || rot>=90) {
            dir = 0;
            if(rot<=45) {
                rot = 45;
            }
            if(rot>=90) {
                rot = 90;
            }
        }
    }

    public int hashCode() {
        return (int)(x+y+r+rot+cy);
    }
}
