package com.anwesome.ui.tvbutton;

import android.app.Activity;
import android.widget.ScrollView;

/**
 * Created by anweshmishra on 28/05/17.
 */

public class TVButtonList {
    private Activity activity;
    private ScrollView scrollView;
    private boolean isShown = false;
    public TVButtonList(Activity activity) {
        this.activity = activity;
        this.scrollView = new ScrollView(activity);
    }
    public void addButton() {
        if(!isShown) {

        }
    }
    public void show() {
        if(!isShown) {
            activity.setContentView(scrollView);
            isShown = true;
        }
    }
}
