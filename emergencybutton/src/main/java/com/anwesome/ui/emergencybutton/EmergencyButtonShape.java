package com.anwesome.ui.emergencybutton;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by anweshmishra on 04/04/17.
 */
public class EmergencyButtonShape {
    private float x,y,size,deg = 0,dir = 0;
    public EmergencyButtonShape(float x,float y,float size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(deg);
        drawTriangle(canvas, paint);
        paint.setColor(Color.WHITE);
        drawEmergencyIcon(canvas,paint);
        paint.setStyle(Paint.Style.STROKE);
        drawTriangle(canvas,paint);
        canvas.rotate(deg);
        canvas.restore();
    }
    private void drawTriangle(Canvas canvas,Paint paint) {
        Path path = new Path();
        path.moveTo(-size/2,size/2);
        path.lineTo(size/2,size/2);
        path.lineTo(0,-size/2);
        path.lineTo(-size/2,size/2);
        canvas.drawPath(path,paint);
    }
    private void drawEmergencyIcon(Canvas canvas,Paint paint) {
        paint.setStrokeWidth(size/16);
        canvas.drawLine(0,-size/4,0,size/4,paint);
        canvas.drawCircle(0,size/4+size/8,size/32,paint);
    }
    public void update() {
        deg+=15*dir;
        if(deg % 360 == 0){
            dir = 0;
        }
    }
    public boolean stop() {
        return dir == 0;
    }
    public void startMoving() {
        dir = 1;
    }
}
