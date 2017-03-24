package com.anwesome.demos.settingsbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 25/03/17.
 */
public class SettingsButton {
    private Activity activity;
    public SettingsButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {

    }
    private class SettingsButtonView extends View {
        public SettingsButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
