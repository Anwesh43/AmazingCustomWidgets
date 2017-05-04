package com.anwesome.ui.dotbarswitch;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 04/05/17.
 */
public class DotbarSwitchShape {
    private StateController stateController = new StateController();
    private int render = 0;
    private float y;
    private  OnSelectionChangeListener onSelectionChangeListener;
    public void setOnSelectionChangeListener(OnSelectionChangeListener onSelectionChangeListener) {
        this.onSelectionChangeListener = onSelectionChangeListener;
    }
    public void setY(float y) {
     this.y = y;
    }
    public void draw(Canvas canvas, Paint paint,float w) {
        if(render == 0) {
            stateController.setSize(w/5);
            stateController.setMaxW(7*w/10);
        }
        canvas.save();
        canvas.translate(0,y);
        DrawingUtil.drawDotBar(canvas,paint,w,stateController.getW(),stateController.getScale());
        canvas.restore();
        render++;
    }
    public void update() {
        stateController.update();
    }
    public void startUpdating() {
        stateController.start();
    }
    public boolean stopUpdating() {
        boolean condition =  stateController.stopped();
        if(condition && onSelectionChangeListener != null) {
            if(stateController.getScale() <= 0) {
                onSelectionChangeListener.onUnselected();
            }
            else {
                onSelectionChangeListener.onUnselected();
            }
        }
        return condition;
    }
    public boolean handleTap(float x,float y) {
        return stateController.handleTap(x,y-this.y);
    }
    public int hashCode() {
        return (int)(y+stateController.getScale()+stateController.getW());
    }
}
