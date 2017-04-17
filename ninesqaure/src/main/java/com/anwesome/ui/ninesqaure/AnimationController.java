package com.anwesome.ui.ninesqaure;

import android.view.View;

/**
 * Created by anweshmishra on 17/04/17.
 */
public class AnimationController {
    private View view;
    private boolean isAnimated = false;
    private NineSquareButton nineSquareButton;
    public AnimationController(View view,NineSquareButton nineSquareButton) {
        this.nineSquareButton = nineSquareButton;
        this.view = view;
    }
    public void animate() {
        if(isAnimated) {
            nineSquareButton.move();
            try {
                Thread.sleep(50);
                view.invalidate();
            }
            catch (Exception ex) {

            }
        }
    }
    public void handleTap(float x,float y) {
        if(!isAnimated) {
            nineSquareButton.startMoving();
            isAnimated = true;
            view.postInvalidate();
        }
    }
}
