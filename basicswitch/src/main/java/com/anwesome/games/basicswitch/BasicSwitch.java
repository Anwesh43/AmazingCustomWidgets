package com.anwesome.games.basicswitch;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 06/03/17.
 */
public class BasicSwitch {
    private Activity activity;
    public BasicSwitch(Activity activity) {
        this.activity = activity;
    }
    public void addSwitchObject() {
        
    }
    public void show() {

    }
    private class BasicSwitchView extends View{
        public BasicSwitchView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }

    }
}
