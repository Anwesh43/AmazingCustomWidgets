package com.anwesome.ui.trashbutton.controller;

import android.view.View;

import com.anwesome.ui.trashbutton.TrashButtonShape;

/**
 * Created by anweshmishra on 16/04/17.
 */
public class AnimationController {
    private View view;
    private boolean isAnimated = false;
    private TrashButtonShape trashButtonShape;
    public AnimationController(View view, TrashButtonShape trashButtonShape) {
        this.view = view;
        this.trashButtonShape = trashButtonShape;
    }
    public void animate() {
        if(isAnimated) {
            trashButtonShape.move();
            if(trashButtonShape.stop()) {
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
            trashButtonShape.startMoving();
            isAnimated = true;
            view.postInvalidate();
        }
    }
}
