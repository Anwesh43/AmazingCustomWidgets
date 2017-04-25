package com.anwesome.ui.directionbutton;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by anweshmishra on 25/04/17.
 */
public class DrawingUtil {
    public static void draw(Canvas canvas, Paint paint,float size,float deg) {
        canvas.save();
        canvas.translate(size,size);
        canvas.rotate(deg+60);
        paint.setColor(Color.parseColor("#00BCD4"));
        for(int i=0;i<2;i++) {
            canvas.save();
            canvas.scale(i*2-1,1);
            Path path = new Path();
            path.moveTo(0,0);
            path.lineTo(size/4,-size/4);
            path.lineTo(0,size/2);
            canvas.drawPath(path,paint);
            canvas.restore();
        }
        canvas.restore();
    }
}
