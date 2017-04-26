package com.anwesome.ui.gpbutton;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 26/04/17.
 */
public class GpButtonShape {
    private MovementController movementController = new MovementController();
    public void draw(Canvas canvas, Paint paint,float w) {
        DrawingUtil.drawGpButton(canvas,paint,w,movementController.getScale(),movementController.getDeg());
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
