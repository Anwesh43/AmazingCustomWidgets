package com.anwesome.games.dotspin;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 16/03/17.
 */
public class DotSpinButton{
    private float x,y,radius,l = 0,deg=0;
    private int mode = -1;
    private DotSpinButton() {

    }
    public static DotSpinButton getInstance(){
        return new DotSpinButton();
    }
    public void setDimensions(float x,float y,float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.parseColor("#212121"));
        paint.setStrokeWidth(7);
        int dir[] = {1,-1};
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(deg);
        paint.setStyle(Paint.Style.FILL);
        for(int i=0;i<2;i++) {
            canvas.save();
            canvas.translate(x,y+l*dir[i]);
            canvas.drawCircle(0,0,radius/10,paint);
            canvas.restore();
        }
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(0,0,radius,paint);
        canvas.restore();
    }
    public void startMoving() {
        mode = 0;
    }
    public void update() {
        switch(mode) {
            case 0:
                l+=radius/12;
                if(l>=radius/3) {
                    l = radius/3;
                    mode = 1;
                }
                break;
            case 1:
                deg+=15;
                if(deg%90 == 0) {
                    mode = 2;
                }
                break;
            case 2:
                l-=radius/12;
                if(l<=0) {
                    l = 0;
                    mode = 3;
                }
                break;
        }
    }
    public boolean stopped() {
        return mode == 3;
    }
    public int hashCode() {
        return (int)(x+y+deg+radius+l);
    }
}
