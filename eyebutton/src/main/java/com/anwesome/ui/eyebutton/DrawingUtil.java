package com.anwesome.ui.eyebutton;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by anweshmishra on 24/04/17.
 */
public class DrawingUtil {
    public static void drawEye(Canvas canvas, Paint paint,float deg,float w) {
        canvas.save();
        canvas.translate(w,w);
        canvas.rotate(deg);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(w/20);
        float a  = w/2,b = w/4,r = w/6;
        canvas.drawArc(new RectF(-a,-b,a,b),0,360,true,paint);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(0,0,r,paint);
        canvas.restore();
    }
}
