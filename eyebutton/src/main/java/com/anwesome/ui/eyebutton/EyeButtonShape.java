package com.anwesome.ui.eyebutton;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 24/04/17.
 */
public class EyeButtonShape {
    private OnClickListener onClickListener;
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
    private MovementController movementController = new MovementController();
    public void draw(Canvas canvas, Paint paint,float w) {
        DrawingUtil.drawEye(canvas,paint,movementController.getDeg(),w);
    }
    public void update() {
        movementController.move();
    }
    public void startUpdating() {
        movementController.startMoving();
    }
    public boolean stopped() {
        boolean condition =  movementController.stopped();
        if(condition && onClickListener != null) {
            onClickListener.onClick();
        }
        return condition;
    }
}
