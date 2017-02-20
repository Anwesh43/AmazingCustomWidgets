package com.anwesome.app.modakbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by anweshmishra on 21/02/17.
 */
public class ModakButton {
    private Activity activity;
    public ModakButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {

    }
    private class ModakButtonView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public ModakButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

            try {
                Thread.sleep(50);
                invalidate();
            }
            catch (Exception ex) {

            }
        }
    }
}
