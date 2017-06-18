package com.anwesome.ui.arrowlinebutton;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 19/06/17.
 */

public class ArrowLineButton {
    public static class ArrowLineButtonView extends View {
        private int time = 0,w,h;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public ArrowLineButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(time == 0) {
                w = canvas.getWidth();
                h = canvas.getHeight();
            }
            time++;
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {

            }
            return true;
        }
    }
    
}
