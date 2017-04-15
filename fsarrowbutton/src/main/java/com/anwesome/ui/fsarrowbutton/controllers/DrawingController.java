package com.anwesome.ui.fsarrowbutton.controllers;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 15/04/17.
 */
public class DrawingController {
    private float x,y,size;
    public DrawingController(float x,float y,float size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }
    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        for(int i=0;i<2;i++) {
            float scale = (2*i-1);
            canvas.save();
            canvas.scale(scale,scale);
            drawArrow(canvas,paint);
            canvas.restore();
        }
        canvas.restore();
    }
    private void drawArrow(Canvas canvas,Paint paint) {
        canvas.save();
        canvas.translate(-size/2,size/2);
        canvas.rotate(45);
        canvas.drawLine(0,0,0,-size/4,paint);
        for(int i=0;i<2;i++) {
            canvas.save();
            canvas.scale(2*i-1,1);
            canvas.drawLine(0, -size / 4, -size / 8, -size / 8, paint);
            canvas.restore();
        }
        canvas.restore();
    }

}
