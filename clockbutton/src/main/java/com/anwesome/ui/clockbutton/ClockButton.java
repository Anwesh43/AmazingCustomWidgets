package com.anwesome.ui.clockbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.MotionEvent;
import android.view.View;

import com.anwesome.ui.clockbutton.controllers.AnimationController;

/**
 * Created by anweshmishra on 20/04/17.
 */
public class ClockButton {
    private Activity activity;
    private AnimationController animationController;
    private ClockShape clockShape = new ClockShape();
    private ClockButtonView clockButtonView;
    public void setColor(int color) {
        clockShape.setColor(color);
    }
    public ClockButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {
        if(clockButtonView == null) {
            clockButtonView = new ClockButtonView(activity);
            animationController = new AnimationController(clockButtonView,clockShape);
        }
    }
    private class ClockButtonView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public ClockButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(clockShape != null && animationController != null) {
                clockShape.draw(canvas,paint,canvas.getWidth()/2);
                animationController.animate();
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                animationController.startAnimating();
            }
            return true;
        }
    }

}
