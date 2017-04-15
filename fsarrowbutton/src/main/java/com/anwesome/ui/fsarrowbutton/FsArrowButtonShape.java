package com.anwesome.ui.fsarrowbutton;

import android.graphics.*;

import com.anwesome.ui.fsarrowbutton.controllers.DrawingController;
import com.anwesome.ui.fsarrowbutton.controllers.StateController;

/**
 * Created by anweshmishra on 15/04/17.
 */
public class FsArrowButtonShape {
    private StateController stateController = new StateController();
    private DrawingController drawingController;
    public FsArrowButtonShape(float size) {
        drawingController = new DrawingController(size,size,size,stateController);
    }
    public void draw(Canvas canvas, Paint paint) {
        drawingController.draw(canvas,paint);
    }
    public void update() {
        stateController.move();
    }
    public void startMoving() {
        stateController.startMoving();
    }
    public boolean stop() {
        return stateController.stop();
    }
}
