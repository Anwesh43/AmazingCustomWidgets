package com.anwesome.ui.watchlikebutton;

import android.view.View;

/**
 * Created by anweshmishra on 19/04/17.
 */
public class AnimationController {
    private boolean isAnimated = false;
    private View view;
    private WatchLikeShape watchLikeShape;
    public AnimationController(View view,WatchLikeShape watchLikeShape) {
        this.view = view;
        this.watchLikeShape = watchLikeShape;
    }
    public void animate() {
        if(isAnimated) {
            watchLikeShape.move();
            if(watchLikeShape.stopped()) {
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
            watchLikeShape.startMoving();
            isAnimated = true;
            view.postInvalidate();
        }
    }
}
