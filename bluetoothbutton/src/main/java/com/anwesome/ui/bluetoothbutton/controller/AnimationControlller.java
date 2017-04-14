package com.anwesome.ui.bluetoothbutton.controller;

import android.view.View;

import com.anwesome.ui.bluetoothbutton.BluetoothButtonShape;

/**
 * Created by anweshmishra on 14/04/17.
 */
public class AnimationControlller {
    private boolean isAnimated = false;
    private View view;
    private BluetoothButtonShape bluetoothButtonShape;
    public AnimationControlller(View view, BluetoothButtonShape bluetoothButtonShape) {
        this.view = view;
        this.bluetoothButtonShape = bluetoothButtonShape;
    }
    public void animate() {
        if(isAnimated) {
            bluetoothButtonShape.update();
            if(bluetoothButtonShape.stop()) {
                isAnimated = false;
            }
            try {
                Thread.sleep(50);
                view.invalidate();
            }
            catch (Exception ex) {

            }
        }
    }
    public void handleTap() {
        if(!isAnimated) {
            isAnimated = true;
            bluetoothButtonShape.startMoving();
            view.postInvalidate();
        }
    }
}
