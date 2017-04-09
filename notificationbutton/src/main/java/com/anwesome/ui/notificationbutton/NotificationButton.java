package com.anwesome.ui.notificationbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 09/04/17.
 */
public class NotificationButton {
    private Activity activity;
    private NotificationButtonView notificationButtonView;
    private NotificationButtonController notificationButtonController;
    public NotificationButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {
        if(notificationButtonView == null) {
            notificationButtonView = new NotificationButtonView(activity);
        }
    }
    private class NotificationButtonView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private boolean isAnimated = false;
        public NotificationButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(notificationButtonController!=null) {
                notificationButtonController.draw(canvas,paint);
                if(isAnimated) {
                    notificationButtonController.startMoving();
                    if(notificationButtonController.stop()) {
                        isAnimated = false;
                    }
                    try {
                        Thread.sleep(50);
                        invalidate();
                    }
                    catch (Exception  ex) {

                    }
                }
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && notificationButtonController!=null && !isAnimated) {
                notificationButtonController.startMoving();
                isAnimated = true;
                postInvalidate();
            }
            return true;
        }
    }
}
