package com.anwesome.app.pathtext;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 20/02/17.
 */
public class PathText {
    private Activity activity;
    public PathText(Activity activity) {
        this.activity = activity;
    }
    public void show() {

    }
    private class PathTextView extends View {
        public PathTextView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            
        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
