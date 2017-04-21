package com.anwesome.ui.crecbutton;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 21/04/17.
 */
public class CrecButtonShape {
    private OnClickListener onClickListener;
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
    private MovementController movementController = new MovementController();
    public void draw(Canvas canvas, Paint paint,float w) {
        DrawingUtil.drawCrec(canvas,paint,w,movementController.getDeg(),movementController.getScale());
    }
    public void update() {
        movementController.update();
    }
    public void startUpdating() {
        movementController.startUpdating();
    }
    public boolean stopped() {
        boolean condition = movementController.stopped();
        if(condition && onClickListener!=null) {
            onClickListener.onClick();
        }
        return condition;
    }
}
