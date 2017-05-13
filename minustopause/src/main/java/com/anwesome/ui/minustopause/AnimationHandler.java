package com.anwesome.ui.minustopause;

import android.animation.ValueAnimator;

/**
 * Created by anweshmishra on 13/05/17.
 */
public class AnimationHandler {
    private ValueAnimator startAnim = ValueAnimator.ofFloat(0,1),endAnim = ValueAnimator.ofFloat(1,0);
    {{
        startAnim.setDuration(500);
        endAnim.setDuration(500);
    }}
    private MinusToPauseView minusToPauseView;
    public AnimationHandler(MinusToPauseView minusToPauseView) {
        this.minusToPauseView = minusToPauseView;
    }
    private void startUpAnim() {

    }
    private void startDownAnim() {

    }
    public void startAnim() {

    }
}
