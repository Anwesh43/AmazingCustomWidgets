package com.anwesome.ui.uiwindow;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

/**
 * Created by anweshmishra on 22/04/17.
 */
public class DrawingUtil {
    public static void drawUiWindow(Canvas canvas, Paint paint,float w,float deg) {
        canvas.save();
        canvas.translate(w,w);
        canvas.rotate(deg);
        paint.setColor(Color.parseColor("#FDD835"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(w/40);
        canvas.drawRect(new RectF(-w/2,-w/3,w/2,w/3),paint);
        paint.setStyle(Paint.Style.FILL);
        Path path = new Path();
        path.moveTo(-w/2,-w/3);
        path.lineTo(-w/2,-w/3-w/10);
        path.lineTo(-w/2+w/6,-w/3-w/10);
        path.lineTo(-w/2+w/6+w/10,-w/3);
        path.lineTo(-w/2,-w/3);
        canvas.drawPath(path,paint);
        canvas.restore();
    }
}
