package com.anwesome.ui.directionbutton.controller;

import android.view.View;

import com.anwesome.ui.directionbutton.DirectionButtonShape;

/**
 * Created by anweshmishra on 25/04/17.
 */
public class AnimationController {
    private View view;
    private DirectionButtonShape directionButtonShape;
    private boolean isAnimated = false;
    public AnimationController(View view, DirectionButtonShape directionButtonShape) {
        this.view = view;
        this.directionButtonShape = directionButtonShape;
    }
    public void animate() {
        if(isAnimated) {
            directionButtonShape.update();
            if(directionButtonShape.stopped()) {
                isAnimated = false;
            }
            try {
                Thread.sleep(50);
                view.invalidate();
            }
            catch(Exception ex) {

            }
        }
    }
    public void startAnimating() {
        if(!isAnimated) {
            directionButtonShape.startUpdating();
            isAnimated = false;
            view.postInvalidate();
        }
    }
}
