package com.anwesome.ui.sharerotbutton;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 18/06/17.
 */

public class ShareRotButton  {
    private class ShareRotButtonView extends View {
        public ShareRotButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {

            }
            return true;
        }
        public void update(float factor) {
            postInvalidate();
        }
    }
}
