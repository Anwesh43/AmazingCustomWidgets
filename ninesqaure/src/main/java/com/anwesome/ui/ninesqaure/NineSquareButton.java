package com.anwesome.ui.ninesqaure;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 17/04/17.
 */
public class NineSquareButton {
    private float x,y,w;
    private StateController stateController;
    public NineSquareButton(float w) {
        this.x = w;
        this.y = w;
        this.w = w;
        this.stateController = new StateController(w);
    }
    public void draw(Canvas canvas, Paint paint) {
        DrawingUtil.drawNineSquare(canvas,paint,stateController,x,y,w);
    }
    public void move() {
        stateController.move();
    }
    public void startMoving() {
        stateController.startMoving();
    }
    public boolean stopped() {
        return stateController.stopped();
    }
}
