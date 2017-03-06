package com.anwesome.games.basicswitch;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.*;

/**
 * Created by anweshmishra on 06/03/17.
 */
public class BasicSwitch {
    private Activity activity;
    private List<SwitchObject> switchObjects = new ArrayList<>();
    private BasicSwitchView basicSwitchView;
    public BasicSwitch(Activity activity) {
        this.activity = activity;
    }
    public void addSwitchObject(OnSelectedListener onSelectedListener) {
        this.switchObjects.add(getSwitchObject(onSelectedListener));
    }
    public SwitchObject getSwitchObject(OnSelectedListener onSelectedListener) {
        return new SwitchObject(onSelectedListener);
    }
    public void show() {
        if(basicSwitchView == null) {
            basicSwitchView = new BasicSwitchView(activity);
            activity.setContentView(basicSwitchView);
        }
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
