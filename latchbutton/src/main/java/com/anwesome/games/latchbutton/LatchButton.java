package com.anwesome.games.latchbutton;

import android.app.Activity;

/**
 * Created by anweshmishra on 09/03/17.
 */
public class LatchButton {
    private Activity activity;
    private LatchButton(Activity activity) {
        this.activity = activity;
    }
    public static LatchButton newInstance(Activity activity) {
        return new LatchButton(activity);
    }
    public void show() {

    }
}
