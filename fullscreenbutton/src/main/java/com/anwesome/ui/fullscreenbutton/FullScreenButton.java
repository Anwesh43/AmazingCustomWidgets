package com.anwesome.ui.fullscreenbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 12/04/17.
 */
public class FullScreenButton {
    private Activity activity;
    private FullScreenButtonView fullScreenButtonView;
    public FullScreenButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {
        if(fullScreenButtonView == null) {
            fullScreenButtonView = new FullScreenButtonView(activity);
        }
    }
    private class FullScreenButtonView extends View {
        public FullScreenButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
