package com.anwesome.ui.gpbutton;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 26/04/17.
 */
public class GpButtonShape {
    private OnSizeChangeListener onSizeChangeListener;
    private MovementController movementController = new MovementController();
    public void draw(Canvas canvas, Paint paint,float w) {
        DrawingUtil.drawGpButton(canvas,paint,w,movementController.getScale(),movementController.getDeg());
    }
    public void setOnSizeChangeListener(OnSizeChangeListener onSizeChangeListener) {
        this.onSizeChangeListener = onSizeChangeListener;
    }
    public void update() {
        movementController.move();
    }
    public void startUpdating() {
        movementController.startMoving();
    }
    public boolean stopped() {
        boolean condition =  movementController.stopped();
        if(condition) {
            if(movementController.getDeg() >= 90) {
                onSizeChangeListener.onIncrease();
            }
            else {
                onSizeChangeListener.onDecrease();
            }
        }
        return condition;
    }
}
