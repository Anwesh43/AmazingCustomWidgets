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
    public void startUpAnim() {

    }
    public void startDownAnim() {

    }
}
