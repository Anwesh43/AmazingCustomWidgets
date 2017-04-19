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
        float radius = w/6;
        paint.setStrokeWidth(w/30);
        paint.setColor(Color.parseColor("#26A69A"));
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(0,0,radius,paint);
        float start = w/6+w/24;
        paint.setStyle(Paint.Style.FILL);
        for(int i=0;i<2;i++) {
            int factor = 2*i-1;
            canvas.save();
            canvas.scale(1,factor);
            paint.setColor(Color.parseColor("#212121"));
            canvas.drawRect(new RectF(-w/8,start,w/8,w/2),paint);
            if(i == 0) {
                float gap = (w/28),y = start+2*gap;
                for(int k=0;k<3;k++) {
                    paint.setColor(Color.WHITE);
                    canvas.drawCircle(0,y,gap/2,paint);
                    y+=2*gap;
                }
            }
            canvas.restore();
        }
        canvas.restore();
    }
}
