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
    private ValueAnimator upAnim = ValueAnimator.ofFloat(0,1),downAnim = ValueAnimator.ofFloat(1,0);
    {{
        upAnim.setDuration(500);
        downAnim.setDuration(500);
        upAnim.addUpdateListener(this);
        upAnim.addListener(this);
        downAnim.addUpdateListener(this);
        downAnim.addListener(this);
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
        upAnim.start();
    }
    private void startDownAnim() {
        downAnim.start();
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
