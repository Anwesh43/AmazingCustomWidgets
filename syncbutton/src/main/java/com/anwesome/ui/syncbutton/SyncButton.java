package com.anwesome.ui.syncbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 02/04/17.
 */
public class SyncButton {
    private Activity activity;
    private SyncShape syncShape;
    public SyncButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {

    }
    private class SyncButtonView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private boolean isAnimated = false;
        public SyncButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(syncShape!=null) {
                syncShape.draw(canvas,paint);
                if(isAnimated) {
                    try {
                        Thread.sleep(50);
                        invalidate();
                    }
                    catch (Exception ex) {

                    }
                }
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(!isAnimated && event.getAction() == MotionEvent.ACTION_DOWN && syncShape!=null) {
                syncShape.startMoving();
                isAnimated = true;
                postInvalidate();
            }
            return true;
        }
    }
}
