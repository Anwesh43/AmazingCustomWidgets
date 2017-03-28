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
        public PinBarButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
