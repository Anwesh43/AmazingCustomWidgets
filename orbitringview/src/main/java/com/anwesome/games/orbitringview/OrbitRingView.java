package com.anwesome.games.orbitringview;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 10/07/17.
 */

public class OrbitRingView extends View {
    private int n=3;
    public OrbitRingView(Context context) {
        super(context);
    }
    public void setN(int n) {
        this.n = Math.max(this.n,n);

    }
    public void onDraw(Canvas canvas) {

    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {

        }
        return true;
    }
}
