package com.anwesome.app.concentriccircleswitch;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 03/03/17.
 */
public class ConcentricCircleSwitch {
    private Activity activity;
    public ConcentricCircleSwitch(Activity activity) {
        this.activity = activity;
    }
    public void show() {

    }
    private class ConcentricCircleView extends View {
        public ConcentricCircleView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
