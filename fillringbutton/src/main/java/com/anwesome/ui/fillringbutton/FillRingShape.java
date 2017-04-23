package com.anwesome.ui.fillringbutton;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 23/04/17.
 */
public class FillRingShape {
    private int render = 0;
    private MovementController movementController;
    private OnFillChangeListener onFillChangeListener;
    public void setOnFillChangeListener(OnFillChangeListener onFillChangeListener) {
        this.onFillChangeListener = onFillChangeListener;
    }
    public void draw(Canvas canvas, Paint paint,float w) {
        if(render == 0) {
            movementController = new MovementController(w/2);
        }
        DrawingUtil.draw(canvas,paint,w,movementController.getDeg(),movementController.getL(),movementController.getA());
        render++;
    }
    public void update() {
        if(movementController!=null) {
            movementController.update();
        }
    }
    public void startUpdating() {
        if(movementController != null) {
            movementController.startUpdating();
        }
    }
    public boolean stopped() {
        boolean condition =  movementController!=null && movementController.stopped();
        if(condition && onFillChangeListener!=null) {
            if(movementController.getDeg() == 0) {
                onFillChangeListener.onUnFill();
            }
            else {
                onFillChangeListener.onFill();
            }
        }
        return condition;
    }
}
