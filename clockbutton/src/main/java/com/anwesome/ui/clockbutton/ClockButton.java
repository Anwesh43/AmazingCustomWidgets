package com.anwesome.ui.clockbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 20/04/17.
 */
public class ClockButton {
    private Activity activity;
    private ClockShape clockShape = new ClockShape();
    public void setColor(int color) {
        clockShape.setColor(color);
    }
    public ClockButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {

    }
    private class ClockButtonView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public ClockButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }

}
