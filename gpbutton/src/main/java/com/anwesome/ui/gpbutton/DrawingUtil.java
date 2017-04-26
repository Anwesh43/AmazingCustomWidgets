package com.anwesome.ui.gpbutton;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by anweshmishra on 26/04/17.
 */
public class DrawingUtil {
    public static void drawGpButton(Canvas canvas, Paint paint,float size,float scale,float deg) {
        canvas.save();
        canvas.translate(size,size);
        canvas.rotate(deg);
        canvas.scale(scale,scale);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#00695C"));
        canvas.drawCircle(0,0,size/6,paint);
        paint.setColor(Color.parseColor("#4CAF50"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(size/12);
        float a = 0;
        for(int i=0;i<3;i++) {
            canvas.drawArc(new RectF(-size/2,-size/2,size/2,size/2),a,a+60,true,paint);
            a += 120;
        }
        canvas.restore();
    }
}
