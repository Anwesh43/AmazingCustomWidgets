package com.anwesome.ui.mutebutton;

import android.graphics.*;

/**
 * Created by anweshmishra on 11/04/17.
 */
public class DrawingUtil {
    public static void drawBlackCircle(Canvas canvas, Paint paint,float size) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#263238"));
        canvas.drawCircle(0,0,size/2+size/10,paint);
    }
    public static void drawWhiteArc(Canvas canvas, Paint paint,float size) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        canvas.drawArc(new RectF(-size/2,-size/2,size/2,size/2),30,120,false,paint);
    }
    public static void drawEllipse(Canvas canvas,Paint paint,float size) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        canvas.drawRoundRect(new RectF(-size/6,-size/4,size/6,size/4),size/8,size/8,paint);
    }
}
