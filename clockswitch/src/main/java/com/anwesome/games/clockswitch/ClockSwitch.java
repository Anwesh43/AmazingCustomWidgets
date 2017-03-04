package com.anwesome.games.clockswitch;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.*;

/**
 * Created by anweshmishra on 05/03/17.
 */
public class ClockSwitch {
    private Activity activity;
    public ClockSwitch(Activity activity) {
        this.activity = activity;
    }
    public void show() {

    }
    private class ClockSwitchView extends View{
        private boolean isAnimated = false;
        private int time = 0;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public ClockSwitchView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(isAnimated) {
                try {
                    Thread.sleep(50);
                    invalidate();
                }
                catch (Exception ex) {

                }
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated) {
                isAnimated = true;
                postInvalidate();
            }
            return true;
        }
    }
}
