package com.anwesome.ui.fsarrowbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import com.anwesome.ui.fsarrowbutton.controllers.AnimationController;

/**
 * Created by anweshmishra on 15/04/17.
 */
public class FsArrowButton {
    private Activity activity;
    private FsArrowButtonShape fsArrowButtonShape;
    private FsArrowButtonView view;
    private AnimationController animationController;
    public FsArrowButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {
        if(view == null) {
            view = new FsArrowButtonView(activity);

        }
    }
    private class FsArrowButtonView extends View {
        public FsArrowButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            
        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
