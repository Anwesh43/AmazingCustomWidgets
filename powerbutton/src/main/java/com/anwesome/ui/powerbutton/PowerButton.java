package com.anwesome.ui.powerbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

/**
 * Created by anweshmishra on 31/03/17.
 */
public class PowerButton {
    private Activity activity;
    private PowerButtonView powerButtonView;
    private PowerButtonShape powerButtonShape;
    private PowerButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {
        if(powerButtonView == null) {
            powerButtonView = new PowerButtonView(activity);
            Point dimension = DimensionsUtil.getDeviceDimension(activity);
            int w = dimension.x;
            activity.addContentView(powerButtonView,new ViewGroup.LayoutParams(w/2,w/2));
            powerButtonShape = PowerButtonShape.getInstance(w/4,w/4,w/4);
        }
    }
    private class PowerButtonView extends View {
        public PowerButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
