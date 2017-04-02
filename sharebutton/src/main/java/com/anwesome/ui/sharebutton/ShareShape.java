package com.anwesome.ui.sharebutton;

import android.graphics.*;

/**
 * Created by anweshmishra on 03/04/17.
 */
public class ShareShape {
    private float x,y,r,deg=0,dir =0,render = 0;
    private float xc[] = new float[3],yc[] = new float[3];
    public ShareShape(float x,float y,float r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }
    public void draw(Canvas canvas,Paint paint) {
        if(render == 0) {
            for(int i = 0;i<3;i++) {
                xc[i] = (i%2)*r-r/2;
                yc[i] = (i-1)*r/2;
            }
        }
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(deg);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(0,0,r,paint);
        paint.setStrokeWidth(r/9);
        paint.setColor(Color.WHITE);
        for(int i=0;i<xc.length;i++) {
            if(i<xc.length-1) {
                canvas.drawLine(xc[i],yc[i],xc[i+1],yc[i+1],paint);
            }
            canvas.drawCircle(xc[i],yc[i],r/8,paint);
        }
        canvas.restore();
        render++;
    }
    public void update() {
        deg+=20*dir;
        if(deg%180 == 0) {
            dir = 0;
        }
    }
    public void startMoving() {
        dir = 1;
    }
    public boolean isStop() {
        return dir == 0;
    }
}
