package com.anwesome.ui.watchlikebutton;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by anweshmishra on 19/04/17.
 */
public class WatchDrawingUtil {
    public static void drawWatch(Canvas canvas, Paint paint,WatchMovementController watchMovementController,float w) {
        canvas.save();
        canvas.translate(w/2,w/2);
        canvas.rotate(watchMovementController.getDeg());
        float radius = w/12;
        paint.setStrokeWidth(w/60);
        paint.setColor(Color.parseColor("#26A69A"));
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(0,0,radius,paint);
        paint.setStyle(Paint.Style.FILL);
        for(int i=0;i<2;i++) {
            int factor = 2*i-1;
            canvas.save();
            canvas.scale(1,factor);
            if(i == 0) {
                float gap = (w/28),y = gap;
                for(int k=0;k<3;k++) {
                    paint.setColor(Color.WHITE);
                    canvas.drawCircle(0,y,gap,paint);
                }
            }
            paint.setColor(Color.BLACK);
            canvas.drawRect(new RectF(-w/24,w/4,w/24,w/2),paint);
            canvas.restore();
        }
        canvas.restore();
    }
}
