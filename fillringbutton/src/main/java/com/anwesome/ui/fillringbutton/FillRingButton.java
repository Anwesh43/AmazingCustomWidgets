package com.anwesome.ui.fillringbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 23/04/17.
 */
public class FillRingButton {
    private FillRingShape fillRingShape = new FillRingShape();
    private Activity activity;
    private FillRingView fillRingView;
    private AnimationController animationController;
    public FillRingButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {
        if(fillRingView == null) {
            fillRingView = new FillRingView(activity);
        }
    }
    private class FillRingView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public FillRingView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            fillRingShape.draw(canvas,paint,canvas.getWidth()/2);
        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
