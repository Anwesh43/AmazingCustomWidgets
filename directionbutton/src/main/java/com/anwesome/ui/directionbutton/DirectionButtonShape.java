package com.anwesome.ui.directionbutton;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.anwesome.ui.directionbutton.controller.MovementController;

/**
 * Created by anweshmishra on 25/04/17.
 */
public class DirectionButtonShape {
    private OnDirectionChangeListener onDirectionChangeListener;
    private MovementController movementController = new MovementController();
    public void draw(Canvas canvas, Paint paint,float w) {
        DrawingUtil.draw(canvas,paint,w,movementController.getDeg());
    }
    public void setOnDirectionChangeListener(OnDirectionChangeListener onDirectionChangeListener) {
        this.onDirectionChangeListener = onDirectionChangeListener;
    }
    public void update() {
        movementController.move();
    }
    public void startUpdating() {
        movementController.startMoving();
    }
    public boolean stopped() {
        boolean stopped =  movementController.stopped();
        if(stopped && onDirectionChangeListener != null) {
            if(movementController.getDeg() == 0) {
                onDirectionChangeListener.onPointingToLeft();
            }
            else {
                onDirectionChangeListener.onPointingToRight();
            }
        }
        return stopped;
    }
}
