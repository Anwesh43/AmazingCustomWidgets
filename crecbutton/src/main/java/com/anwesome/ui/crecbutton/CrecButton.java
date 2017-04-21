package com.anwesome.ui.crecbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 21/04/17.
 */
public class CrecButton {
    private Activity activity;
    private CrecButtonShape crecButtonShape = new CrecButtonShape();
    private CrecButtonView crecButtonView;
    private AnimationController animationController;
    public CrecButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {
        if(crecButtonView == null) {
            crecButtonView = new CrecButtonView(activity);
        }
    }
    private class CrecButtonView extends View {
        public CrecButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
