package com.anwesome.games.crosslineview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 14/07/17.
 */

public class CrossLineView extends View {
    private int w,h,time = 0;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public CrossLineView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
        }
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
