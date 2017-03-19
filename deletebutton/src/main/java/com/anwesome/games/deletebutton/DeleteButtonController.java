package com.anwesome.games.deletebutton;

import android.graphics.*;

/**
 * Created by anweshmishra on 20/03/17.
 */
public class DeleteButtonController {
    private boolean stopped = false;
    private float x,y,size,l= 0,deg = 0,bounds;private int mode=0;
    public DeleteButtonController(float x,float y,float size,float bounds) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.bounds = bounds;
    }
    public boolean isStopped() {
        return stopped;
    }
    public void draw(Canvas canvas,Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(deg);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#FF9800"));
        canvas.drawCircle(0,0,size/2,paint);
        paint.setColor(Color.parseColor("#BDBDBD"));
        paint.setStrokeWidth(size/40);
        canvas.drawLine(-size/10,0,0,-l,paint);
        canvas.drawLine(0,-l,size/10,0,paint);
        canvas.restore();
    }
    public void update() {
        switch(mode) {
            case 0:
                l+=size/30;
                if(l>=(2*size)/15) {
                    l = (2*size)/15;
                    mode = 1;
                }
                break;
            case 1:
                deg+=10;
                if(deg>=90) {
                    deg = 90;
                    mode = 2;
                }
                break;
            case 2:
                x+=size/5;
                if(x>=bounds+size/2 && !stopped) {
                    stopped = true;
                }
                break;
            default:
                break;
        }
    }
    public int hashCode() {
        return (int)(x+y+size+bounds);
    }
}
