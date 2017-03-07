package com.anwesome.games.circledarrowbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 08/03/17.
 */
public class CircledArrowButton {
    private Activity activity;
    public CircledArrowButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {

    }
    private class CircledArrowView extends View {
        private boolean isAnimated = false;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public CircledArrowView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated) {

            }
            return true;
        }
    }
}
