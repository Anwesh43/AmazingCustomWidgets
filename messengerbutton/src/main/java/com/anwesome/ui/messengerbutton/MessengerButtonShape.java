package com.anwesome.ui.messengerbutton;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.anwesome.ui.messengerbutton.controller.DrawingController;
import com.anwesome.ui.messengerbutton.controller.StateController;

/**
 * Created by anweshmishra on 13/04/17.
 */
public class MessengerButtonShape {
    private float x,y,size;
    private DrawingController drawingController;
    private StateController stateController = new StateController();
    public MessengerButtonShape(float x,float y,float size) {
        drawingController = new DrawingController(x,y,size,stateController);
    }
    public void draw(Canvas canvas, Paint paint) {
        drawingController.draw(canvas,paint);
    }
    public void move() {
        stateController.move();
    }
    public boolean stop() {
        return stateController.stop();
    }
    public void startMoving() {
        stateController.startMoving();
    }
}
