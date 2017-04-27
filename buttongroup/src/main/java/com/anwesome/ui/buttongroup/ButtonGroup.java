package com.anwesome.ui.buttongroup;

import android.app.Activity;
import android.graphics.Color;

/**
 * Created by anweshmishra on 27/04/17.
 */
public class ButtonGroup {
    private Activity activity;
    private int color = Color.parseColor("#0277BD");
    public ButtonGroup(Activity activity) {
        this.activity = activity;
    }
    public void setColor(int color) {
        this.color = color;
    }
    public void show() {
        
    }
}
