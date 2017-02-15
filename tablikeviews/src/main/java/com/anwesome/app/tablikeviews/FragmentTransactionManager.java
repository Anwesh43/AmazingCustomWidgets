package com.anwesome.app.tablikeviews;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

/**
 * Created by anweshmishra on 15/02/17.
 */
public class FragmentTransactionManager {
    private static int transaction_done = 0;
    public static void doTransaction(AppCompatActivity activity, Fragment fragment,FrameLayout frameLayout) {
        FragmentManager manager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        if(transaction_done == 0) {
            fragmentTransaction.add(frameLayout.getId(),fragment);
        }
        else {
            fragmentTransaction.replace(frameLayout.getId(),fragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
        transaction_done++;
    }
}
