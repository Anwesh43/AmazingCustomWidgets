package com.anwesome.games.hamburgbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 18/03/17.
 */
public class HamburgerButton {
    private Activity activity;
    public HamburgerButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {

    }
    private class HamburgerButtonView extends View {
        private boolean isAnimated = false;
        public HamburgerButtonView(Context context) {
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
