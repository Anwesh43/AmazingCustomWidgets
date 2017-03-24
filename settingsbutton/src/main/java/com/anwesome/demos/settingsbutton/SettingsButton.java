package com.anwesome.demos.settingsbutton;

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
 * Created by anweshmishra on 25/03/17.
 */
public class SettingsButton {
    private Activity activity;
    private SettingsButtonView settingsButtonView;
    private SettingsButtonController settingsButtonController;
    public SettingsButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {
        if(settingsButtonView == null) {
            settingsButtonView = new SettingsButtonView(activity);
            Point size = DimensionsUtil.getDeviceDimension(activity);
            int w = size.x,h = size.y;
            settingsButtonController = SettingsButtonController.getInstance(w/4,w/4,w/2);
            activity.addContentView(settingsButtonView,new ViewGroup.LayoutParams(w/2,w/2));
        }
    }
    private class SettingsButtonView extends View {
        private boolean isAnimated = false;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public SettingsButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            settingsButtonController.draw(canvas,paint);
            if(isAnimated) {
                settingsButtonController.update();
                if(settingsButtonController.stopped()) {
                    isAnimated = false;
                }
                try{
                    Thread.sleep(50);
                    invalidate();
                }
                catch (Exception ex) {

                }
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated) {
                settingsButtonController.startMoving();
                isAnimated = true;
                postInvalidate();
            }
            return true;
        }
    }
}
