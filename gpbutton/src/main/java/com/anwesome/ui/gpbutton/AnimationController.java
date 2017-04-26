package com.anwesome.ui.gpbutton;

import android.view.View;

/**
 * Created by anweshmishra on 26/04/17.
 */
public class AnimationController {
    private View view;
    private GpButtonShape gpButtonShape;
    private boolean isAnimated = false;
    public AnimationController(View view,GpButtonShape gpButtonShape) {
        this.view = view;
        this.gpButtonShape = gpButtonShape;
    }
    public void animate() {
        if(isAnimated) {
            gpButtonShape.update();
            if(gpButtonShape.stopped()) {
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
            isAnimated = true;
            gpButtonShape.startUpdating();
            view.postInvalidate();
        }
    }
}
