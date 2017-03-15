package com.anwesome.games.playpause;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by anweshmishra on 15/03/17.
 */
public class PlayButton extends ButtonUi{
    protected float getInitialScale() {
        return 1;
    }
    protected void drawButton(Canvas canvas, Paint paint,float w,float h) {
        Path path = new Path();
        path.moveTo(w/2,0);
        path.lineTo(-w/2,h/2);
        path.lineTo(-w/2,-h/2);
        canvas.drawPath(path,paint);
    }
}
