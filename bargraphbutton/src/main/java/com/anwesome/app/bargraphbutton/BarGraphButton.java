package com.anwesome.app.bargraphbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.*;

/**
 * Created by anweshmishra on 02/03/17.
 */
public class BarGraphButton {
    private Activity activity;
    public BarGraphButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {

    }
    private class BarGraphView extends View {
        public BarGraphView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
