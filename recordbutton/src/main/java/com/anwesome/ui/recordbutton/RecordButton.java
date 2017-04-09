package com.anwesome.ui.recordbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 10/04/17.
 */
public class RecordButton {
    private Activity activity;
    private RecordButtonView recordButtonView;
    public RecordButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {
        if(recordButtonView  == null) {
            recordButtonView = new RecordButtonView(activity);
        }
    }
    private class RecordButtonView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private boolean isAnimated = false;
        public RecordButtonView(Context context) {
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
