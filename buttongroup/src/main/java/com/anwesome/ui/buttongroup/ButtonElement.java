package com.anwesome.ui.buttongroup;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 27/04/17.
 */
public class ButtonElement {
    private String title = "";
    private OnSelectionListener onSelectionListener;
    public void setOnSelectionListener(OnSelectionListener onSelectionListener) {
        this.onSelectionListener = onSelectionListener;
    }
    private ButtonStateController buttonStateController;
    public ButtonElement(String title) {
        this.title = title;
    }
    public void setDimension(float x,float y,float w,float h) {
        buttonStateController = new ButtonStateController(x,y,w,h);
    }
    public void draw(Canvas canvas, Paint paint,int color) {
        DrawingUtil.drawButton(canvas,paint,title,buttonStateController,color);
    }
    public void update() {
        if(buttonStateController!=null) {
            buttonStateController.update();
        }
    }
    public boolean stopped() {
        boolean condition =  buttonStateController!=null && buttonStateController.stopped();
        if(condition && onSelectionListener != null) {
            if(buttonStateController.getScale() >= 1) {
                onSelectionListener.onSelected();
            }
            else {
                onSelectionListener.onUnSelected();
            }
        }
        return condition;
    }
    public boolean handleTap(float x,float y) {
        return buttonStateController!=null && buttonStateController.handleTap(x,y);
    }
    public int hashCode() {
        return title.hashCode()+buttonStateController.hashCode();
    }
}
