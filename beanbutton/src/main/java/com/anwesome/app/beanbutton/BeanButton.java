package com.anwesome.app.beanbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.*;
/**
 * Created by anweshmishra on 18/02/17.
 */
public class BeanButton {
    private Activity activity;
    public BeanButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {

    }
    private class BeanButtonView extends View {
        public BeanButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            try {

            }
            catch (Exception ex) {

            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
