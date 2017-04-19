package com.anwesome.ui.watchlikebutton;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 19/04/17.
 */
public class WatchLikeShape {
    private final WatchMovementController watchMovementController = new WatchMovementController();
    private WatchClickListener watchClickListener;
    public void setWatchClickListener(WatchClickListener watchClickListener) {
        this.watchClickListener = watchClickListener;
    }
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
        boolean condition =  watchMovementController.stopped();
        if(condition) {
            watchClickListener.onClick();
        }
        return condition;
    }
}
