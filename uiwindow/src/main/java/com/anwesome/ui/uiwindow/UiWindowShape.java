package com.anwesome.ui.uiwindow;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.anwesome.ui.uiwindow.controllers.MovementController;

/**
 * Created by anweshmishra on 22/04/17.
 */
public class UiWindowShape {
    private OnClickListener onClickListener;
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
    private MovementController movementController = new MovementController();
    public void draw(Canvas canvas, Paint paint,float w) {
        DrawingUtil.drawUiWindow(canvas,paint,w,movementController.getDeg());
    }
    public void update() {
        movementController.move();
    }
    public void startUpdating() {
        movementController.startMoving();
    }
    public boolean stopped() {
        boolean isStopped = movementController.stopped();
        if(isStopped && onClickListener!=null) {
            onClickListener.onClick();
        }
        return isStopped;
    }
}
