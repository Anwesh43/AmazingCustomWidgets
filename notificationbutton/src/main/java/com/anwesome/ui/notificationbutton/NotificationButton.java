package com.anwesome.ui.notificationbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 09/04/17.
 */
public class NotificationButton {
    private Activity activity;
    private NotificationButtonView notificationButtonView;
    public NotificationButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {
        if(notificationButtonView == null) {
            notificationButtonView = new NotificationButtonView(activity);
        }
    }
    private class NotificationButtonView extends View {
        public NotificationButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {

            }
            return true;
        }
    }
}
