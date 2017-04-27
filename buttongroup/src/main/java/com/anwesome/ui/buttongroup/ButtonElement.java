package com.anwesome.ui.buttongroup;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 27/04/17.
 */
public class ButtonElement {
    private int color;
    private String title = "";
    private ButtonStateController buttonStateController;

    public ButtonElement(String title,int color) {
        this.title = title;
        this.color = color;
    }
    public void setDimension(float x,float y,float w,float h) {
        buttonStateController = new ButtonStateController(x,y,w,h);
    }
    public void draw(Canvas canvas, Paint paint) {
        DrawingUtil.drawButton(canvas,paint,title,buttonStateController,color);
    }
    public void update() {
        if(buttonStateController!=null) {
            buttonStateController.update();
        }
    }
    public boolean stopped() {
        return buttonStateController!=null && buttonStateController.stopped();
    }
    public boolean handleTap(float x,float y) {
        return buttonStateController!=null && buttonStateController.handleTap(x,y);
    }
    public int hashCode() {
        return title.hashCode()+buttonStateController.hashCode();
    }
}
