package com.anwesome.demos.settingsbutton;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by anweshmishra on 25/03/17.
 */
public class SettingsButtonController {
    private float x,y,r,deg = 0,dir = 0;
    private SettingsButtonController(float x,float y,float w) {
        this.x = x;
        this.y = y;
        this.r = w/2;
    }
    public static SettingsButtonController getInstance(float x,float y,float w) {
        return new SettingsButtonController(x,y,w);
    }
    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(deg);
        drawSpikes(canvas,paint);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(0,0,r/2,paint);
        canvas.restore();
    }
    public void drawSpikes(Canvas canvas,Paint paint) {
        float newR = r/2+r/5,dir = 1;
        float xc = newR,yc = 0;
        Path path = new Path();
        path.moveTo(xc,yc);
        for(int i=0;i<360;i+=5) {
            if(i%20 == 0) {
                newR +=r/6*dir;
                dir *=-1;
            }
            else {
                xc = newR*(float)(Math.cos(i*Math.PI/180));
                yc = newR*(float)(Math.sin(i*Math.PI/180));
                path.lineTo(xc,yc);
            }
        }
        paint.setColor(Color.GRAY);
        canvas.drawPath(path,paint);
    }
    public void update() {
        deg+=15*dir;
        if(deg%90 == 0) {
            dir = 0;
        }
    }
    public boolean stopped() {
        return dir == 0;
    }
    public void startMoving() {
        dir = 1;
    }
    public int hashCode() {
        return (int)(x+y+dir+deg+r);
    }
}
