package com.anwesome.ui.mutebutton;

import android.graphics.*;

/**
 * Created by anweshmishra on 11/04/17.
 */
public class DrawingUtil {
    public static void drawBlackCircle(Canvas canvas, Paint paint,float size) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0,0,size/2,paint);
    }
    public static void drawWhiteArc(Canvas canvas, Paint paint,float size) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        canvas.drawArc(new RectF(-size/2,-size/2,size/2,size/2),60,60,false,paint);
    }
    public static void drawEllipse(Canvas canvas,Paint paint,float size) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        canvas.drawArc(new RectF(-size/5,-size/3,size/5,size/3),0,360,true,paint);
    }
}
