package com.anwesome.app.prokbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.View;

/**
 * Created by anweshmishra on 12/02/17.
 */
public class ProkButton {
    private Activity activity;
    public ProkButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {

    }
    private class ProkButtonView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public ProkButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
        
        }
    }
}
