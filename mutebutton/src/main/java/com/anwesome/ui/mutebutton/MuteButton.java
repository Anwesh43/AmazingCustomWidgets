package com.anwesome.ui.mutebutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 11/04/17.
 */
public class MuteButton {
    private Activity activity;
    public MuteButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {

    }
    private class MuteButtonView extends View {
        private boolean isAnimated = false;
        public MuteButtonView(Context context) {
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
