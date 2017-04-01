package com.anwesome.ui.syncbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 02/04/17.
 */
public class SyncButton {
    private Activity activity;
    public SyncButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {

    }
    private class SyncButtonView extends View {
        public SyncButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
