package com.anwesome.ui.crecbutton;

import android.view.View;

/**
 * Created by anweshmishra on 21/04/17.
 */
public class AnimationController {
    private boolean isAnimated = false;
    private View view;
    private CrecButtonShape crecButtonShape;
    public AnimationController(View view,CrecButtonShape crecButtonShape) {
        this.view = view;
        this.crecButtonShape = crecButtonShape;
    }
    public void animate() {
        if(isAnimated) {
            crecButtonShape.update();
            if(crecButtonShape.stopped()) {
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
            crecButtonShape.startUpdating();
            isAnimated = true;
            view.postInvalidate();
        }
    }
}
