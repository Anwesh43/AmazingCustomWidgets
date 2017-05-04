package com.anwesome.ui.dotbarswitch;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 04/05/17.
 */
public class DotbarSwitch {
    private Activity activity;
    private DotbarSwitchView dotbarSwitchView;
    public DotbarSwitch(Activity activity) {
        this.activity = activity;
    }
    public void show() {
        if(dotbarSwitchView == null) {
            dotbarSwitchView = new DotbarSwitchView(activity);
        }
    }
    private class DotbarSwitchView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public DotbarSwitchView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
