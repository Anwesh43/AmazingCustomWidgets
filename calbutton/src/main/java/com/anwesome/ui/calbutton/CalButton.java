package com.anwesome.ui.calbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 05/04/17.
 */
public class CalButton {
    private Activity activity;
    private CalButtonView view;
    public CalButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {
        if(view == null) {
            view = new CalButtonView(activity);
        }
    }
    private class CalButtonView extends View {
        public CalButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
