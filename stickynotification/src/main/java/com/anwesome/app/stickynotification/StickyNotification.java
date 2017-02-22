package com.anwesome.app.stickynotification;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 23/02/17.
 */
public class StickyNotification {
    private Activity mActivity;
    public StickyNotification(Activity activity) {
        mActivity = activity;
    }
    public void show() {

    }
    private class StickyNotificationView extends View {
        public StickyNotificationView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
