package com.anwesome.ui.clockbutton.utils;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 20/04/17.
 */
public class ClockDrawingUtil {
    public static void drawClock(Canvas canvas, Paint paint,float w,float deg,int color) {
        canvas.save();
        canvas.translate(w,w);
        paint.setStrokeWidth(w/30);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setColor(color);
        canvas.drawCircle(0,0,w/2,paint);
        canvas.save();
        canvas.rotate(deg);
        canvas.drawLine(0,0,0,-w/3,paint);
        canvas.restore();
        canvas.save();
        canvas.rotate(deg/12);
        canvas.drawLine(0,0,0,-w/5,paint);
        canvas.restore();
        canvas.restore();
    }
}
