package com.anwesome.ui.clockbutton.controllers;

import android.view.View;

import com.anwesome.ui.clockbutton.ClockShape;

/**
 * Created by anweshmishra on 20/04/17.
 */
public class AnimationController {
    private boolean isAnimated = false;
    private View view;
    private ClockShape clockShape;
    public AnimationController(View view, ClockShape clockShape) {
        this.view = view;
        this.clockShape = clockShape;
    }
    public void animate() {
        if(isAnimated) {
            clockShape.move();
            if(clockShape.stopped()) {
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
    public void startAnimating() {
        if(!isAnimated) {
            clockShape.startMoving();
            isAnimated = true;
            view.postInvalidate();
        }
    }
}
