package com.anwesome.games.tripathbutton;

import android.graphics.*;

/**
 * Created by anweshmishra on 17/03/17.
 */
public class PathFollowingBall {
    private TriPath triPath;
    private final int SIDES = 3,STEPS=10;
    private float deg = 60,r=0,maxR = 0,dir = 0;
    private int i = 0;
    private PointF pivot = new PointF();
    public PathFollowingBall(TriPath triPath) {
        this.triPath = triPath;
        initProperties();
    }
    public void initProperties() {
        maxR = triPath.getA();
        pivot = triPath.getVertexAt(i);
    }
    public void draw(Canvas canvas, Paint paint) {
        float x = pivot.x+(float)(r*Math.cos(deg*Math.PI/180)),y = pivot.y+(float)(r*Math.sin(deg*Math.PI/180));
        paint.setColor(Color.parseColor("#e53935"));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x,y,maxR/12,paint);
    }
    public void startMoving() {
        dir = dir == 0?1:dir;
    }
    public void update() {
        r+=(maxR/STEPS)*dir;
        if(r>=maxR) {
            deg+=(360/SIDES);
            r = 0;
            dir = 0;
            i++;
            i%=SIDES;
            pivot = triPath.getVertexAt(i);
        }
    }
    public boolean stopped() {
        return dir == 0;
    }
    public int hashCode() {
        return pivot.hashCode()+triPath.hashCode()+(int)(deg+r+maxR+i+dir);
    }
}
