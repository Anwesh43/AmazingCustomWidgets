package com.anwesome.ui.bluetoothbutton.controller;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;

import java.util.ArrayList;
import com.anwesome.ui.bluetoothbutton.utils.LineMaker;

/**
 * Created by anweshmishra on 14/04/17.
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
        paint.setTextSize(size/30);
        paint.setStyle(Paint.Style.STROKE);
        LineMaker.drawLine(new ArrayList<PointF>(){{
            add(new PointF(0,0));
            add(new PointF(0,-size/2));
            add(new PointF(size/4,-size/4));
            add(new PointF(-size/4,size/4));
        }});
        canvas.restore();
    }
}
