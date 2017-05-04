package com.anwesome.ui.dotbarswitch;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 04/05/17.
 */
public class DotbarSwitchShape {
    private StateController stateController = new StateController();
    private int render = 0;
    public void draw(Canvas canvas, Paint paint,float w) {
        if(render == 0) {
            stateController.setMaxW(7*w/10);
        }
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
}
