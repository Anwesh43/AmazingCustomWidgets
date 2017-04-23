package com.anwesome.ui.fillringbutton;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 23/04/17.
 */
public class FillRingShape {
    private int render = 0;
    private MovementController movementController;
    public void draw(Canvas canvas, Paint paint,float w) {
        if(render == 0) {
            movementController = new MovementController(w);
        }
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
        return movementController!=null && movementController.stopped();
    }
}
