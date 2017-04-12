package com.anwesome.ui.messengerbutton.controller;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 13/04/17.
 */
public class AnimationController {
    private boolean isAnimated = false;
    private View view;
    private AnimationControllerListener controllerListener;
    public void setControllerListener(AnimationControllerListener animationControllerListener) {
        this.controllerListener = animationControllerListener;
    }
    public AnimationController(View view,AnimationControllerListener animationControllerListener) {
        this.view = view;
        this.controllerListener = animationControllerListener;
    }
    public void update() {
        if(isAnimated) {
            if(controllerListener!=null) {
                controllerListener.animate();
                if(controllerListener.stop()) {
                    isAnimated = false;
                }
            }
            try {
                Thread.sleep(50);
                view.invalidate();
            }
            catch (Exception ex) {

            }
        }
    }
    public void handleTap(int action) {
        if(action == MotionEvent.ACTION_DOWN) {
            isAnimated = true;
            if(controllerListener!=null) {
                controllerListener.handleTap();
            }
            view.postInvalidate();
        }
    }
    public interface AnimationControllerListener {
        void animate();
        boolean stop();
        void handleTap();
    }
}
