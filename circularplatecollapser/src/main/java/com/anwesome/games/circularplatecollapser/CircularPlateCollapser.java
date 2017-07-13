package com.anwesome.games.circularplatecollapser;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 13/07/17.
 */

public class CircularPlateCollapser {
    private static class CircularPlateCollapserView  extends View{
        private int time = 0,w,h;
        public CircularPlateCollapserView(Context context) {
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
