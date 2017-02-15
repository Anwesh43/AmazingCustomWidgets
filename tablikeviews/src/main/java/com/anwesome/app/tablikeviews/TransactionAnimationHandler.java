package com.anwesome.app.tablikeviews;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;

/**
 * Created by anweshmishra on 15/02/17.
 */
public class TransactionAnimationHandler implements ValueAnimator.AnimatorUpdateListener,Animator.AnimatorListener{
    private View view;
    public TransactionAnimationHandler(View view) {
        this.view = view;
    }
    public void onAnimationUpdate(ValueAnimator animator) {
        view.setRotation(360*(float)animator.getAnimatedValue());
        view.setScaleX(1.0f*(float)animator.getAnimatedValue());
        view.setScaleY(1.0f*(float)animator.getAnimatedValue());
    }
    public void onAnimationEnd(Animator animator) {

    }
    public void onAnimationRepeat(Animator animator) {

    }
    public void onAnimationStart(Animator animator) {

    }
    public void onAnimationCancel(Animator animator) {

    }
}
