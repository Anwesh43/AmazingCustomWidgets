package com.anwesome.ui.ninesqaure;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 17/04/17.
 */
public class NineSquare {
    private Activity activity;
    public NineSquare(Activity activity) {
        this.activity = activity;
    }
    public void show() {

    }
    private class NineSquareView extends View {
        public NineSquareView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
