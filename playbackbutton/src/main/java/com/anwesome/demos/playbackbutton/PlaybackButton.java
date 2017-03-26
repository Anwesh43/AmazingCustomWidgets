package com.anwesome.demos.playbackbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 27/03/17.
 */
public class PlaybackButton {
    private Activity activity;
    public PlaybackButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {

    }
    private class PlaybackButtonView extends View {
        public PlaybackButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
