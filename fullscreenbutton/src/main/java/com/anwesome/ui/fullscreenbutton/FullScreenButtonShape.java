package com.anwesome.ui.fullscreenbutton;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 12/04/17.
 */
public class FullScreenButtonShape {
    private StateHandler stateHandler = new StateHandler();
    private DrawingController drawingController;
    private OnFullButtonClickListener onFullButtonClickListener;
    private float x,y,size;
    public void setOnFullButtonClickListener(OnFullButtonClickListener onFullButtonClickListener) {
        this.onFullButtonClickListener = onFullButtonClickListener;
    }
    public FullScreenButtonShape(float x,float y,float size) {
        this.x = x;
        this.y = y;
        this.size = size;
        drawingController = new DrawingController(stateHandler,x,y,size);
    }
    public void draw(Canvas canvas, Paint paint) {
        drawingController.draw(canvas,paint);
    }
    public void handleTap() {
        stateHandler.startMoving();
    }
    public void move() {
        stateHandler.move();
    }
    public boolean stop() {
        boolean condition =  stateHandler.stop();
        if(condition && onFullButtonClickListener != null) {
            if(stateHandler.getDeg() >= 180) {
                onFullButtonClickListener.onexpand();
            }
            else {
                onFullButtonClickListener.onshrink();
            }
        }
        return condition;
    }
    public interface OnFullButtonClickListener {
        void onexpand();
        void onshrink();
    }
}
