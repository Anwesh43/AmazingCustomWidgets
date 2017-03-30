package com.anwesome.ui.powerbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 31/03/17.
 */
public class PowerButton {
    private Activity activity;
    private PowerButtonView powerButtonView;
    private PowerButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {
        if(powerButtonView == null) {
            powerButtonView = new PowerButtonView(activity);
        }
    }
    private class PowerButtonView extends View {
        public PowerButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
