package com.anwesome.games.latchbutton;

import android.graphics.*;

/**
 * Created by anweshmishra on 09/03/17.
 */
public class Latch {
    private float x,y,rSize,cSize,rh1,rh2,deg = -90,dir = 0,initDeg=-90;
    public Latch(float w,float h) {
        this.x = w/2;
        this.y = h/2;
        this.cSize = w/4;
        this.rSize = w/4;
        this.rh1 = 15;
        this.rh2 = 2*this.rh1;
    }
    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#FFD54F"));
        canvas.drawArc(new RectF(-cSize/2,-cSize/2,cSize/2,cSize/2),initDeg,initDeg+180,true,paint);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawArc(new RectF(-cSize/2,-cSize/2,cSize/2,cSize/2),initDeg,(deg-initDeg),true,paint);
        canvas.save();
        canvas.rotate(deg);
        drawHandle(canvas,paint);
        canvas.restore();
        canvas.restore();
    }
    public void update() {
        deg+=dir*20;
        if(Math.abs(deg)>=90) {
            deg = dir*90;
            dir = 0;
        }
    }
    public boolean stopped() {
        return dir == 0;
    }
    private void drawHandle(Canvas canvas,Paint paint) {
        PointPath path = new PointPath();
        path.moveTo(getPointInCircle(cSize,-rh1));
        path.lineTo(getPointInCircle(cSize+rSize/2,-rh1));
        path.lineTo(getPointInCircle(cSize+rSize/2,-rh2));
        path.lineTo(getPointInCircle(cSize+rSize,-rh2));
        path.lineTo(getPointInCircle(cSize+rSize,rh2));
        path.lineTo(getPointInCircle(cSize+rSize/2,rh2));
        path.lineTo(getPointInCircle(cSize+rSize/2,rh1));
        path.lineTo(getPointInCircle(cSize,rh1));
        canvas.drawPath(path,paint);
    }
    private PointF getPointInCircle(float r,float d) {
        return new PointF((float)(r*Math.cos(d*Math.PI/180)),(float)(r*Math.sin(d*Math.PI/180)));
    }
    public void handleTap() {
        dir = deg == -90?1:-1;
    }
}
