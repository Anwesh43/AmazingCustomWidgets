package com.anwesome.ui.trashbutton.util;

import android.graphics.*;

/**
 * Created by anweshmishra on 16/04/17.
 */
public class DrawingUtil {
    public static void renderTrashButton(Canvas canvas, Paint paint,float w,float deg) {
        canvas.save();
        canvas.translate(w,w);
        canvas.rotate(deg);
        drawTrashButtonShape(canvas,paint,w);
        canvas.restore();
    }
    private static void drawTrashButtonShape(Canvas canvas,Paint paint,float w) {
        paint.setStrokeWidth(w/80);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(new RectF(-w/3,-w/2,w/3,w/2),paint);
        canvas.drawRect(new RectF(-w/15,-w/2-w/24,w/15,-w/2),paint);
        for(int i=0;i<4;i++) {
            float x = (i*w/6)-w/4;
            canvas.save();
            canvas.drawLine(x,-w/3,x,w/3,paint);
            canvas.restore();
        }
    }
}
