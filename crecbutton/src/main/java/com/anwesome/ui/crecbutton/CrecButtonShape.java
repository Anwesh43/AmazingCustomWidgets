package com.anwesome.ui.crecbutton;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 21/04/17.
 */
public class CrecButtonShape {
    private MovementController movementController = new MovementController();
    public void draw(Canvas canvas, Paint paint,float w) {
        DrawingUtil.drawCrec(canvas,paint,w,movementController.getDeg(),movementController.getScale());
    }
    public void update() {
        movementController.update();
    }
    public void startUpdating() {
        movementController.startUpdating();
    }
    public boolean stopped() {
        return movementController.stopped();
    }
}
