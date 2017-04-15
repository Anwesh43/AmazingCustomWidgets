package com.anwesome.ui.trashbutton;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.anwesome.ui.trashbutton.controller.StateController;
import com.anwesome.ui.trashbutton.util.DrawingUtil;

/**
 * Created by anweshmishra on 16/04/17.
 */
public class TrashButtonShape {
    private TrashButtonClickListener trashButtonClickListener;
    private StateController stateController = new StateController();
    public void draw(Canvas canvas, Paint paint,float w) {
        DrawingUtil.renderTrashButton(canvas,paint,w,stateController.getDeg());
    }
    public void setTrashButtonOnClickListener(TrashButtonClickListener trashButtonOnClickListener) {
        this.trashButtonClickListener = trashButtonOnClickListener;
    }
    public void move() {
        stateController.move();
    }
    public boolean stop() {
        boolean condition = stateController.stop();
        if(condition && trashButtonClickListener!=null) {
            trashButtonClickListener.onClick();
        }
        return condition;
    }
    public void startMoving() {
        stateController.startMoving();
    }
    public interface TrashButtonClickListener {
        void onClick();
    }
}
