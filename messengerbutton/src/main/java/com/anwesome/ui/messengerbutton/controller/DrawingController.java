package com.anwesome.ui.messengerbutton.controller;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

/**
 * Created by anweshmishra on 13/04/17.
 */
public class DrawingController {
    private float x,y,size;
    private StateController stateController;
    public DrawingController(float x,float y,float size,StateController stateController) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.stateController = stateController;
    }
    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(stateController.getDeg());
        drawPath(canvas,paint);
        canvas.restore();
    }
    private void drawPath(Canvas canvas,Paint paint) {
        paint.setColor(Color.parseColor("#00E676"));
        Path path = new Path();
        path.addArc(new RectF(-size/2,-size/2,size/2,size/2),0,120);
        lineToPath(path,size/2+size/5,135);
        lineToPath(path,size/2,150);
        path.addArc(new RectF(-size/2,-size/2,size/2,size/2),150,210);
        canvas.drawPath(path,paint);
    }
    private void lineToPath(Path path,float r,float theta) {
        float x = r*(float)(Math.cos(theta*Math.PI/180)),y = r*(float)(Math.sin(theta*Math.PI/180));
        path.lineTo(x,y);
    }
}

