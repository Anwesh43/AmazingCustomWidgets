package com.anwesome.ui.eyebutton;

import android.view.View;

/**
 * Created by anweshmishra on 24/04/17.
 */
public class AnimationController {
    private boolean isAnimated = false;
    private View view;
    private EyeButtonShape eyeButtonShape;
    public AnimationController(View view,EyeButtonShape eyeButtonShape) {
        this.view = view;
        this.eyeButtonShape = eyeButtonShape;
    }
    public void animate() {
        if(isAnimated) {
            eyeButtonShape.update();
            if(eyeButtonShape.stopped()) {
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
            eyeButtonShape.startUpdating();
            isAnimated = true;
            view.postInvalidate();
        }
    }
}
