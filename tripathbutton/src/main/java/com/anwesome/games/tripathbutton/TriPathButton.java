package com.anwesome.games.tripathbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 17/03/17.
 */
public class TriPathButton {
    private Activity activity;
    private TriPath triPath;
    private PathFollowingBall pathFollowingBall;
    private TriPathButtonView triPathButtonView;
    public TriPathButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {
        if(triPathButtonView==null) {
            triPathButtonView = new TriPathButtonView(activity);

        }
    }
    private class TriPathButtonView extends View {
        private boolean isAnimated = false;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public TriPathButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(isAnimated) {
                try {
                    Thread.sleep(50);
                    invalidate();
                }
                catch(Exception ex) {

                }
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated) {
                isAnimated = true;
                postInvalidate();
            }
            return true;
        }

    }
}
