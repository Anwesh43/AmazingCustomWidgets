package com.anwesome.games.playpause;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 15/03/17.
 */
public class PauseButton extends ButtonUi{
    protected float getInitialScale() {
        return 0;
    }
    protected float getInitialDeg() {
        return -180;
    }
    protected  void drawButton(Canvas canvas, Paint paint,float w,float h) {
        paint.setStrokeWidth(7);
        canvas.drawLine(-w/4,-h/2,-w/4,h/2,paint);
        canvas.drawLine(w/4,-h/2,w/4,h/2,paint);
    }
}
