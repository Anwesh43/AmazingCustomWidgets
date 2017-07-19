package com.anwesome.games.concoppocircview;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 19/07/17.
 */

public class ConcOppoCircView extends View {
    private int n=3,w,h,time = 0;
    public void setN(int n) {
        this.n = Math.max(n,this.n);
    }
    public ConcOppoCircView(Context context) {
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
        if(event.getAction() == MotionEvent.ACTION_DOWN) {

        }
        return true;
    }
}
