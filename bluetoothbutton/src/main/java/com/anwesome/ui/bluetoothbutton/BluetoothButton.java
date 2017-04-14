package com.anwesome.ui.bluetoothbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 14/04/17.
 */
public class BluetoothButton {
    private BluetoothButtonView bluetoothButtonView;
    private Activity activity;
    public BluetoothButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {
        if(bluetoothButtonView == null) {

        }
    }
    private class BluetoothButtonView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public BluetoothButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {

            }
            return true;
        }
    }
}
