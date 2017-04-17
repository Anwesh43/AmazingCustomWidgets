package com.anwesome.ui.ninesqaure;

import android.graphics.*;
/**
 * Created by anweshmishra on 17/04/17.
 */
public class DrawingUtil {
    public static void drawNineSquare(Canvas canvas, Paint paint,StateController stateController,float x,float y,float size) {
        paint.setColor(Color.parseColor("#1565C0"));
        canvas.save();
        canvas.translate(x,y);
        float w = stateController.getGap();
        float sqX = -w/2+w/18+w/9,sqY = -w/2+w/18+w/9;
        for(int i=0;i<9;i++){
            canvas.save();
            canvas.translate(sqX+(w/6)*(i%3),sqY+(w/6)*(i/3));
            canvas.rotate(stateController.getDeg());
            canvas.drawRect(new RectF(-size/12,-size/12,size/12,size/12),paint);
            canvas.restore();
        }
        canvas.restore();
    }
}
