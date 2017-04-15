package com.anwesome.ui.fsarrowbutton.controllers;

import android.view.MotionEvent;
import android.view.View;

import com.anwesome.ui.fsarrowbutton.FsArrowButtonShape;

/**
 * Created by anweshmishra on 15/04/17.
 */
public class AnimationController {
    public boolean isAnimated = false;
    private View view;
    private FsArrowButtonShape fsArrowButtonShape;
    public AnimationController(View view, FsArrowButtonShape fsArrowButtonShape) {
        this.fsArrowButtonShape = fsArrowButtonShape;
        this.view = view;
    }
    public void animate() {
        if(isAnimated) {
            fsArrowButtonShape.update();
            if(fsArrowButtonShape.stop()) {
                isAnimated = false;
            }
            try {
               Thread.sleep(50);
                view.invalidate();
            }
            catch (Exception ex) {

            }
        }
    }
    public void handleTap(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN && event.getAction() == MotionEvent.ACTION_DOWN) {
            fsArrowButtonShape.startMoving();
            isAnimated = true;
            view.postInvalidate();
        }
    }
}
