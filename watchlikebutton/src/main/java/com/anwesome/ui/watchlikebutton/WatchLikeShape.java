package com.anwesome.ui.watchlikebutton;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 19/04/17.
 */
public class WatchLikeShape {
    private WatchMovementController watchMovementController = new WatchMovementController();
    public void draw(Canvas canvas, Paint paint,float w) {
        WatchDrawingUtil.drawWatch(canvas,paint,watchMovementController,w);
    }
    public void move() {
        watchMovementController.move();
    }
    public void startMoving() {
        watchMovementController.startMoving();
    }
    public boolean stopped() {
        return watchMovementController.stopped();
    }
}
