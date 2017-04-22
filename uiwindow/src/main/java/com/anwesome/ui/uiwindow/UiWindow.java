package com.anwesome.ui.uiwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.anwesome.ui.uiwindow.controllers.AnimationController;

/**
 * Created by anweshmishra on 22/04/17.
 */
public class UiWindow {
    private Activity activity;
    private AnimationController animationController;
    private UiWindowShape uiWindowShape = new UiWindowShape();
    private UiWindowView uiWindowView;
    public UiWindow(Activity activity) {
        this.activity = activity;
    }
    public void show() {
        if(uiWindowView == null) {
            uiWindowView = new UiWindowView(activity);
            animationController = new AnimationController(uiWindowView,uiWindowShape);
        }
    }
    private class UiWindowView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public UiWindowView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            uiWindowShape.draw(canvas,paint,canvas.getWidth()/2);
            animationController.animate();
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                animationController.startAnimating();
            }
            return true;
        }
    }
}
