package com.anwesome.games.latchbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 09/03/17.
 */
public class LatchButton {
    private Activity activity;
    private LatchButton(Activity activity) {
        this.activity = activity;
    }
    public static LatchButton newInstance(Activity activity) {
        return new LatchButton(activity);
    }
    public void show() {

    }
    private class LatchButtonView extends View {
        private boolean isAnimated = false;
        public LatchButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(isAnimated) {
                try {
                    Thread.sleep(50);
                    invalidate();
                }
                catch(Exception ex) {

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
