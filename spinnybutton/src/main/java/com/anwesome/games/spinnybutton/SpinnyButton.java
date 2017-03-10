package com.anwesome.games.spinnybutton;

import android.app.Activity;

/**
 * Created by anweshmishra on 11/03/17.
 */
public class SpinnyButton {
    private Activity activity;
    private SpinnyButton(Activity activity) {
        this.activity = activity;
    }
    public static SpinnyButton getInstance(Activity activity) {
        return new SpinnyButton(activity);
    }
    public void show() {

    }
}
