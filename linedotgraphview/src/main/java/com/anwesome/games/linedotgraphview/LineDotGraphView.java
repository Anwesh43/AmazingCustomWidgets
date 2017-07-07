package com.anwesome.games.linedotgraphview;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 07/07/17.
 */

public class LineDotGraphView extends View {
    private int data[],time = 0,w,h;
    public LineDotGraphView(Context context,int[] data) {
        super(context);
        this.data = data;
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
