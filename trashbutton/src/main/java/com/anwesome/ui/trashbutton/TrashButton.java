package com.anwesome.ui.trashbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 16/04/17.
 */
public class TrashButton {
    private Activity activity;
    private TrashButtonShape trashButtonShape;
    public TrashButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {

    }
    private class TrashButtonView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public TrashButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
