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
    private OnSelectionChangeListener onSelectionChangeListener;
    private StateController stateController = new StateController();
    public void setOnSelectionChangeListener(OnSelectionChangeListener onSelectionChangeListener) {
        this.onSelectionChangeListener = onSelectionChangeListener;
    }
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
        boolean condition =  stateController.stop();
        if(condition && onSelectionChangeListener!=null) {
            if(stateController.selected()) {
                onSelectionChangeListener.onSelect();
            }
            else {
                onSelectionChangeListener.onUnselect();
            }
        }
        return condition;
    }
    public void startMoving() {
        stateController.startMoving();
    }
    public interface OnSelectionChangeListener {
        void onSelect();
        void onUnselect();
    }
}
