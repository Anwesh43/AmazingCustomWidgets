package com.anwesome.ui.trashbutton;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.anwesome.ui.trashbutton.controller.StateController;

/**
 * Created by anweshmishra on 16/04/17.
 */
public class TrashButtonShape {
    private StateController stateController = new StateController();
    public void draw(Canvas canvas, Paint paint,float w) {

    }
    public void move() {
        stateController.move();
    }
    public boolean stop() {
        boolean condition = stateController.stop();
        return condition;
    }
    public void startMoving() {
        stateController.startMoving();
    }
}
