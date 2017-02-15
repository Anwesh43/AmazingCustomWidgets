package com.anwesome.app.tablikeviews;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 15/02/17.
 */
public class FragmentTransactionManager {
    private static ValueAnimator inAnim = ValueAnimator.ofFloat(0,1.0f),outAnim = ValueAnimator.ofFloat(1.0f,0);
    private static Map<View,Integer> viewContainer = new HashMap<>();
    private static  TransactionEndHandler transactionEndHandler;
    public static void setOnTrsactionEndListener(TransactionEndHandler handler) {
        transactionEndHandler = handler;
    }
    public static void switchView(final AppCompatActivity activity,final View view1,final View view2) {
        inAnim = ValueAnimator.ofFloat(0,1.0f);
        outAnim = ValueAnimator.ofFloat(1.0f,0);
        outAnim.setDuration(1000);
        inAnim.setDuration(1000);
        TransactionAnimationHandler transactionAnimationHandler2 = new TransactionAnimationHandler(view2){
            public void onAnimationEnd(Animator animator) {
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.INVISIBLE);
                inAnim.start();
            }
        };
        TransactionAnimationHandler transactionAnimationHandler1 = new TransactionAnimationHandler(view1){
            public void onAnimationEnd(Animator animator) {
                inAnim = null;
                outAnim = null;
                if(transactionEndHandler!=null) {
                    transactionEndHandler.handle();
                }
            }
        };
        inAnim.addUpdateListener(transactionAnimationHandler1);
        inAnim.addListener(transactionAnimationHandler1);
        outAnim.addListener(transactionAnimationHandler2);
        outAnim.addUpdateListener(transactionAnimationHandler2);
        outAnim.start();

    }
    public static void addViews(Activity activity, ConcurrentLinkedQueue<TabElement> tabs, int w, int h, int y) {
        for(TabElement tab:tabs) {
            View view = tab.getFragment();
            activity.addContentView(view, new ViewGroup.LayoutParams(w, h));
            view.setX(0);
            view.setY(y);
            view.setVisibility(View.INVISIBLE);
        }
    }
    public static void setDefaultView(TabElement tabElement) {
        if(tabElement!=null  && tabElement.getFragment()!=null) {
            tabElement.getFragment().setVisibility(View.VISIBLE);
        }
    }
    public interface TransactionEndHandler {
        void handle();
    }
}
