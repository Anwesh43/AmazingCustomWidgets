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
    public void setY(float y) {
     this.y = y;
    }
    public void draw(Canvas canvas, Paint paint,float w) {
        if(render == 0) {
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
        return stateController.stopped();
    }
    public int hashCode() {
        return (int)(y+stateController.getScale()+stateController.getW());
    }
}
