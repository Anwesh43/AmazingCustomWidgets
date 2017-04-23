package com.anwesome.ui.fillringbutton;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by anweshmishra on 23/04/17.
 */
public class DrawingUtil {
    public static void draw(Canvas canvas, Paint paint,float w,float deg,float l,float a) {
        float finalL = (w/2)/Constants.L_SCALE,radius = w/12;
        canvas.save();
        canvas.translate(w,w);
        canvas.rotate(deg);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(w/40);
        for(int i=0;i<4;i++) {
            canvas.save();
            canvas.rotate(i*90);
            paint.setColor(Color.GRAY);
            drawRing(canvas,paint,finalL,360,radius);
            paint.setColor(Color.GREEN);
            drawRing(canvas,paint,l,a,radius);
            canvas.restore();
        }
        canvas.restore();
    }
    private static void drawRing(Canvas canvas,Paint paint,float len,float endDeg,float radius) {
        canvas.drawLine(0,0,len,0,paint);
        float centerX = len+radius;
        canvas.drawArc(new RectF(centerX-radius,-radius,centerX+radius,radius),180,endDeg,false,paint);
    }
}
