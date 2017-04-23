package com.anwesome.ui.fillringbutton;

import android.view.View;

/**
 * Created by anweshmishra on 23/04/17.
 */
public class AnimationController {
    private View view;
    private FillRingShape fillRingShape;
    private boolean isAnimated = false;
    public AnimationController(View view,FillRingShape fillRingShape) {
        this.view = view;
        this.fillRingShape = fillRingShape;
    }
    public void animate() {
        if(isAnimated) {
            fillRingShape.update();
            if(fillRingShape.stopped()) {
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
            fillRingShape.startUpdating();
            isAnimated = true;
            view.postInvalidate();
        }
    }
}
