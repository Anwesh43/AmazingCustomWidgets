package com.anwesome.ui.fullscreenbutton;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 12/04/17.
 */
public class FullScreenButtonShape {
    private StateHandler stateHandler = new StateHandler();
    private float x,y,size;
    public FullScreenButtonShape(float x,float y,float size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }
    public void draw(Canvas canvas, Paint paint) {

    }
    public void handleTap() {
        stateHandler.startMoving();
    }
    public void move() {
        stateHandler.move();
    }
    public boolean stop() {
        return stateHandler.stop();
    }
}
