package com.anwesome.ui.crecbutton;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 21/04/17.
 */
public class DrawingUtil {
    public static void drawCrec(Canvas canvas, Paint paint,float w,float deg,float scale) {
        canvas.save();
        canvas.translate(w,w);
        canvas.rotate(deg);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(0,0,w,paint);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(0,0,w/2,paint);
        canvas.save();
        canvas.translate(0,0);
        canvas.scale(scale,scale);
        paint.setColor(Color.CYAN);
        canvas.drawCircle(0,0,w/2,paint);
        canvas.restore();
        for(int i=0;i<1;i++) {
            canvas.save();
            canvas.rotate(i*90);
            paint.setColor(Color.WHITE);
            canvas.drawCircle(3*w/4,0,w/20,paint);
            canvas.restore();
        }
        canvas.restore();
    }
}
