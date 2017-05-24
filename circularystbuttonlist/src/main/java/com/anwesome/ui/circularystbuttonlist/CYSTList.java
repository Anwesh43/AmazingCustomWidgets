package com.anwesome.ui.circularystbuttonlist;

import android.app.Activity;

/**
 * Created by anweshmishra on 25/05/17.
 */

public class CYSTList {
    private Activity activity;
    private boolean isShown = false;
    public CYSTList(Activity activity) {
        this.activity = activity;
    }
    public void show() {
        if(!isShown) {
            isShown = true;
        }
    }
    public void addButton() {
        if(!isShown) {

        }
    }
}
