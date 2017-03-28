package com.anwesome.demos.pinbar;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.*;

/**
 * Created by anweshmishra on 29/03/17.
 */
public class PinBarButton  {
    private Activity activity;
    public PinBarButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {

    }
    private class PinBarButtonView extends View {
        private boolean isAnimated = false;
        public PinBarButtonView(Context context) {
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
