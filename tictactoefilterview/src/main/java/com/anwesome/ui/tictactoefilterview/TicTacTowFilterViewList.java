package com.anwesome.ui.tictactoefilterview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ScrollView;

/**
 * Created by anweshmishra on 22/05/17.
 */
public class TicTacTowFilterViewList {
    private Activity activity;
    private boolean isShown = false;
    private ScrollView scrollView;
    private ListLayout listLayout;
    public TicTacTowFilterViewList(Activity activity) {
        this.activity = activity;
        scrollView = new ScrollView(activity);
        listLayout = new ListLayout(activity);
        scrollView.addView(listLayout,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
    public void addImage(Bitmap bitmap) {
        if(!isShown) {
            listLayout.addImage(bitmap);
        }
    }
    public void show() {
        if(!isShown) {
            activity.setContentView(scrollView);
            isShown = true;
        }
    }
}
