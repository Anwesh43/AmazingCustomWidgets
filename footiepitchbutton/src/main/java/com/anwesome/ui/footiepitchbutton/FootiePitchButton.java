package com.anwesome.ui.footiepitchbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 06/04/17.
 */
public class FootiePitchButton {
    private Activity activity;
    private FootiePitchView footiePitchView;
    public FootiePitchButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {
        if(footiePitchView == null) {
            footiePitchView = new FootiePitchView(activity);
        }
    }
    private class FootiePitchView extends View {
        public FootiePitchView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
