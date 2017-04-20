package com.anwesome.ui.clockbutton;

import android.graphics.*;
import com.anwesome.ui.clockbutton.controllers.ClockMovementController;
import com.anwesome.ui.clockbutton.utils.ClockDrawingUtil;

/**
 * Created by anweshmishra on 20/04/17.
 */
public class ClockShape {
    private ClockMovementController clockMovementController = new ClockMovementController();
    private int color = Color.parseColor("#009688");
    public ClockShape(int color) {
        this.color = color;
    }
    public void draw(Canvas canvas, Paint paint,float size) {
        ClockDrawingUtil.drawClock(canvas,paint,size,clockMovementController.getDeg(),color);
    }
    public void move() {
        clockMovementController.move();
    }
    public void startMoving() {
        clockMovementController.startMoving();
    }
    public boolean stopped() {
        return clockMovementController.stopped();
    }
}
