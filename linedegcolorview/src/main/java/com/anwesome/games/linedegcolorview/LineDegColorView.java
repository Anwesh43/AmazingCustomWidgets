package com.anwesome.games.linedegcolorview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 27/06/17.
 */

public class LineDegColorView extends View {
    private int[] colors;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int time=0,w,h;
    public LineDegColorView(Context context,int[] colors) {
        super(context);
        this.colors = colors;
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
        }
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {

        }
        return true;
    }
}
