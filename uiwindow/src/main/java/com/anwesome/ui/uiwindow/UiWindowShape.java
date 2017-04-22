package com.anwesome.ui.uiwindow;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.anwesome.ui.uiwindow.controllers.MovementController;

/**
 * Created by anweshmishra on 22/04/17.
 */
public class UiWindowShape {
    private MovementController movementController = new MovementController();
    public void draw(Canvas canvas, Paint paint,float w) {

    }
    public void update() {
        movementController.move();
    }
    public void startUpdating() {
        movementController.startMoving();
    }
    public boolean stopped() {
        boolean isStopped = movementController.stopped();
        if(isStopped) {

        }
        return isStopped;
    }
}
