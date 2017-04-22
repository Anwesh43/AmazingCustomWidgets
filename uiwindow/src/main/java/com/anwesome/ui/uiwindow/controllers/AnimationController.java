package com.anwesome.ui.uiwindow.controllers;

import android.view.View;

import com.anwesome.ui.uiwindow.UiWindowShape;

/**
 * Created by anweshmishra on 22/04/17.
 */
public class AnimationController {
    private boolean isAnimated = false;
    private View view;
    private UiWindowShape uiWindowShape;
    public AnimationController(View view,UiWindowShape uiWindowShape) {
        this.uiWindowShape = uiWindowShape;
        this.view = view;
    }
    public void animate() {
        if(isAnimated) {
            uiWindowShape.update();
            if(uiWindowShape.stopped()) {
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
            uiWindowShape.startUpdating();
            isAnimated = true;
            view.postInvalidate();
        }
    }
}
