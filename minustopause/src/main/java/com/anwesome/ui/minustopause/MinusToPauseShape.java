package com.anwesome.ui.minustopause;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 13/05/17.
 */
public class MinusToPauseShape {
    private float gap,maxGap,deg;
    private int render = 0;
    public void draw(Canvas canvas, Paint paint,int w) {
        paint.setColor(Color.parseColor("#0277BD"));
        if(render == 0) {
            maxGap = w/4;
            paint.setStrokeWidth(maxGap/10);
        }
        paint.setStyle(Paint.Style.STROKE);
        canvas.save();
        canvas.translate(w/2,w/2);
        canvas.rotate(deg);
        for(int i=0;i<2;i++) {
            canvas.save();
            canvas.translate(0,gap*(i*2-1));
            canvas.drawLine(-w/4,0,w/4,0,paint);
            canvas.restore();
        }
        canvas.restore();
        render++;
    }
    public void update(float factor) {
        deg = 90*factor;
        gap = maxGap*factor;
    }
}
