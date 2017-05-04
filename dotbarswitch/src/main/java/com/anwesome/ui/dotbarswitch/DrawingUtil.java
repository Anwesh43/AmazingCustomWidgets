package com.anwesome.ui.dotbarswitch;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 04/05/17.
 */
public class DrawingUtil {
    public static void drawDotBar(Canvas canvas, Paint paint,float w,float l,float scale) {
        paint.setColor(Color.parseColor("#0D47A1"));
        paint.setStrokeWidth(w/30);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(w/10,w/10,w/10,paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(w/10);
        canvas.save();
        canvas.translate(w/10,w/10);
        canvas.scale(scale,scale);
        canvas.drawCircle(0,0,w/10,paint);
        canvas.restore();
        canvas.drawLine(3*w/10,w/10,3*w/10+l,w/10,paint);
    }
}
