package com.anwesome.ui.ninesqaure;

import android.graphics.*;
/**
 * Created by anweshmishra on 17/04/17.
 */
public class DrawingUtil {
    public static void drawNineSquare(Canvas canvas, Paint paint,float x,float y,float w) {
        paint.setColor(Color.parseColor("#1565C0"));
        canvas.save();
        canvas.translate(x,y);
        float sqX = -w/2+w/18+w/9,sqY = -w/2+w/18+w/9;
        for(int i=0;i<9;i++){
            canvas.save();
            canvas.translate(sqX+(w/6)*(i%3),sqY+(w/6)*(i/3));
            canvas.drawRect(new RectF(-w/12,-w/12,w/12,w/12),paint);
            canvas.restore();
        }
        canvas.restore();
    }
}
