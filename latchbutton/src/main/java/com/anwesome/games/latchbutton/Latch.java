package com.anwesome.games.latchbutton;

import android.graphics.*;

/**
 * Created by anweshmishra on 09/03/17.
 */
public class Latch {
    private LatchSelectedListener latchSelectedListener;
    private float x,y,rSize,cSize,rh1,rh2,deg = -90,dir = 0,initDeg=-90;
    public Latch(float w,float h) {
        this.x = w/2;
        this.y = h/2;
        this.cSize = w/8;
        this.rSize = (3*w/8);
        this.rh1 = 15;
        this.rh2 = this.rh1+5;
    }
    public void setLatchSeleectedListener(LatchSelectedListener latchSeleectedListener) {
        this.latchSelectedListener = latchSeleectedListener;
    }
    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        paint.setColor(Color.parseColor("#FFD54F"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(30);
        canvas.drawArc(new RectF(-cSize,-cSize,cSize,cSize),initDeg,180,true,paint);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawArc(new RectF(-cSize,-cSize,cSize,cSize),initDeg,(deg-initDeg),true,paint);
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
            if(latchSelectedListener!=null) {
                if (deg == -90) {
                    latchSelectedListener.onUnSelected();
                } else {
                    latchSelectedListener.onSelected();
                }
            }
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
