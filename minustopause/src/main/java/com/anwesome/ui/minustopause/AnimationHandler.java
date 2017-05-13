package com.anwesome.ui.minustopause;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;

/**
 * Created by anweshmishra on 13/05/17.
 */
public class AnimationHandler extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener{
    private boolean isAnimated = false;
    private int dir = 0;
    private ValueAnimator startAnim = ValueAnimator.ofFloat(0,1),endAnim = ValueAnimator.ofFloat(1,0);
    {{
        startAnim.setDuration(500);
        endAnim.setDuration(500);
        startAnim.addUpdateListener(this);
        startAnim.addListener(this);
        endAnim.addUpdateListener(this);
        endAnim.addListener(this);
    }}
    private MinusToPauseView minusToPauseView;
    public AnimationHandler(MinusToPauseView minusToPauseView) {
        this.minusToPauseView = minusToPauseView;
    }
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float factor = (float)valueAnimator.getAnimatedValue();
        minusToPauseView.update(factor);
    }
    public void onAnimationEnd(Animator animator) {
        if(isAnimated) {
            isAnimated = false;
        }
    }
    private void startUpAnim() {

    }
    private void startDownAnim() {

    }
    public void startAnim() {
        if(!isAnimated) {
            isAnimated = true;
            if(dir == 0) {
                startUpAnim();
            }
            else {
                startDownAnim();
            }
            dir = dir == 0?1:0;
        }
    }
}
