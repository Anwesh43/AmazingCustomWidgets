package com.anwesome.ui.directionbutton;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.anwesome.ui.directionbutton.controller.MovementController;

/**
 * Created by anweshmishra on 25/04/17.
 */
public class DirectionButtonShape {
    private MovementController movementController = new MovementController();
    public void draw(Canvas canvas, Paint paint,float w) {
        DrawingUtil.draw(canvas,paint,w,movementController.getDeg());
    }
    public void update() {
        movementController.move();
    }
    public void startUpdating() {
        movementController.startMoving();
    }
    public boolean stopped() {
        return movementController.stopped();
    }
}
