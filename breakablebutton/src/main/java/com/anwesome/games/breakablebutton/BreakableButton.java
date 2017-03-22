package com.anwesome.games.breakablebutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 23/03/17.
 */
public class BreakableButton  {
    private Activity activity;
    public BreakableButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {

    }
    private class BreakableButtonView extends View {
        public BreakableButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
