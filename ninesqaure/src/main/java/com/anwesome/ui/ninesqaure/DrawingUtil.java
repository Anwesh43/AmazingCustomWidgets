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
        canvas.rotate(stateController.getDeg());
        float w = stateController.getGap();
        float sqX = -size/2-w/6,sqY = -size/2-w/6;
        for(int i=0;i<9;i++){
            canvas.save();
            canvas.translate(sqX+(w/6)*(i%3),sqY+(w/6)*(i/3));
            canvas.drawRect(new RectF(-size/18,-size/24,size/24,size/18),paint);
            canvas.restore();
        }
        canvas.restore();
    }
}
