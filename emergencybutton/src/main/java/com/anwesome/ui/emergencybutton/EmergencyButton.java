package com.anwesome.ui.emergencybutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 04/04/17.
 */
public class EmergencyButton {
    private Activity activity;
    private EmergencyButtonShape emergencyButtonShape;
    private EmergencyButtonShapeView emergencyButtonShapeView;
    public EmergencyButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {
        if(emergencyButtonShapeView == null) {
            emergencyButtonShapeView = new EmergencyButtonShapeView(activity);
        }
    }
    private class EmergencyButtonShapeView extends View {
        public EmergencyButtonShapeView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
