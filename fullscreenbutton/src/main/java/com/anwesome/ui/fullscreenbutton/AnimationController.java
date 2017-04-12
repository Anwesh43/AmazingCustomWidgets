package com.anwesome.ui.fullscreenbutton;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 12/04/17.
 */
public class AnimationController {
    private boolean isAnimated = false;
    private View view;
    private ShapeAnimationListener shapeAnimationListener;
    public AnimationController(View view,ShapeAnimationListener shapeAnimationListener) {
        this.view = view;
        this.shapeAnimationListener = shapeAnimationListener;
    }
    public void animate() {
        if(isAnimated && shapeAnimationListener!=null) {
            if (this.shapeAnimationListener != null) {
                shapeAnimationListener.animateShape();
            }
            try {
                Thread.sleep(50);
                view.invalidate();
            }
            catch (Exception ex) {

            }
        }
    }
    public void startAnimating(MotionEvent event) {
        if(shapeAnimationListener!=null && event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated) {
            shapeAnimationListener.startShapeAnimation();
            isAnimated = true;
            view.postInvalidate();
        }
    }
    public interface ShapeAnimationListener {
        void animateShape();
        void startShapeAnimation();
    }
}
