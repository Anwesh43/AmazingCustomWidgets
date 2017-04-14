package com.anwesome.ui.bluetoothbutton;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.anwesome.ui.bluetoothbutton.controller.DrawingController;
import com.anwesome.ui.bluetoothbutton.controller.StateController;

/**
 * Created by anweshmishra on 14/04/17.
 */
public class BluetoothButtonShape {
    private DrawingController drawingController;
    private StateController stateController = new StateController();
    public BluetoothButtonShape(float x,float y,float size) {
        drawingController = new DrawingController(x,y,size,stateController);
    }
    public void draw(Canvas canvas, Paint paint) {
        drawingController.draw(canvas,paint);
    }
    public void update() {
        stateController.move();
    }
    public boolean stop() {
        return stateController.stop();
    }
    public void startMoving() {
        stateController.startMoving();
    }
}
