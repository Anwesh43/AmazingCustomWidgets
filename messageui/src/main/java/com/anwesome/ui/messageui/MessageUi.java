package com.anwesome.ui.messageui;

import android.graphics.*;

/**
 * Created by anweshmishra on 01/04/17.
 */
public class MessageUi {
    private float x,y,size,deg = 0,dir = 0;
    public MessageUi(float x,float y,float size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }
    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(deg);
        drawMessageShape(canvas,paint);
        canvas.restore();

    }
    private void drawMessageShape(Canvas canvas,Paint paint) {
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(size/30);
        Path path = new Path();
        path.moveTo(-size/2,-size/2);
        path.lineTo(size/2,-size/2);
        path.lineTo(size/2,size/2);
        path.lineTo(size/3,size/2);
        path.lineTo(size/3,size/2+size/6);
        path.lineTo(size/6,size/2);
        path.lineTo(-size/2,size/2);
        path.lineTo(-size/2,-size/2);
        canvas.drawPath(path,paint);
        paint.setStyle(Paint.Style.FILL);
        for(int i=0;i<3;i++) {
            canvas.drawCircle((i-1)*size/4,0,size/20,paint);
        }
    }
    public void startMoving() {
        dir = 1;
    }
    public void update() {
        deg+=dir*36;
        if(deg  == 360) {
            dir = 0;
            deg = 0;
        }
    }
    public boolean stopped() {
        return dir == 0;
    }
    public int hashCode() {
        return (int)(x+y+size+deg+dir);
    }
}
