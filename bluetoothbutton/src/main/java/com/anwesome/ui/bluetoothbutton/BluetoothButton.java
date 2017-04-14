package com.anwesome.ui.bluetoothbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.anwesome.ui.bluetoothbutton.controller.AnimationControlller;
import com.anwesome.ui.dimensionsutil.DimensionsUtil;

/**
 * Created by anweshmishra on 14/04/17.
 */
public class BluetoothButton {
    private BluetoothButtonView bluetoothButtonView;
    private Activity activity;
    private BluetoothButtonShape bluetoothButtonShape;
    private AnimationControlller animationControlller;
    private BluetoothButtonShape.OnSelectionChangeListener onSelectionChangeListener;
    public BluetoothButton(Activity activity) {
        this.activity = activity;
    }
    public void setOnSelectionChangeListener(BluetoothButtonShape.OnSelectionChangeListener onSelectionChangeListener) {
        this.onSelectionChangeListener = onSelectionChangeListener;
        if(bluetoothButtonShape!=null) {
            bluetoothButtonShape.setOnSelectionChangeListener(onSelectionChangeListener);
        }
    }
    public void show(int x,int y) {
        if(bluetoothButtonView == null) {
            Point size = DimensionsUtil.getDeviceDimension(activity);
            bluetoothButtonView = new BluetoothButtonView(activity);
            int w = size.x;
            activity.addContentView(bluetoothButtonView,new ViewGroup.LayoutParams(w/2,w/2));
            bluetoothButtonShape = new BluetoothButtonShape(w/4,w/4,w/4);
            animationControlller = new AnimationControlller(bluetoothButtonView,bluetoothButtonShape);
            if(onSelectionChangeListener!=null) {
                bluetoothButtonShape.setOnSelectionChangeListener(onSelectionChangeListener);
            }
        }
        bluetoothButtonView.setX(x);
        bluetoothButtonView.setY(y);
    }
    private class BluetoothButtonView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public BluetoothButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(bluetoothButtonShape!=null && animationControlller!=null) {
                bluetoothButtonShape.draw(canvas,paint);
                animationControlller.animate();
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && animationControlller!=null) {
                animationControlller.handleTap();
            }
            return true;
        }
    }
}
