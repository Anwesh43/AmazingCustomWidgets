package com.anwesome.app.lockbuttons;

import android.app.Activity;
import android.content.Context;

/**
 * Created by anweshmishra on 25/02/17.
 */
public class LockButton {
    private Activity activity;
    private LockButtonType lockButtonType;
    public LockButton(Activity activity,LockButtonType lockButtonType){
        this.activity = activity;
        this.lockButtonType = lockButtonType;
    }
    public void show() {

    }
}
