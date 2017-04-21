package com.anwesome.ui.crecbutton;

/**
 * Created by anweshmishra on 21/04/17.
 */
public class MovementController {
    private float deg = 0,dir = 0,scale=0;
    public void update() {
        deg+=dir*36;
        scale+=dir*0.2f;
        if(deg>=180 || deg<=0) {
            dir *= -1;
        }
    }
    public void startUpdating() {
        if(dir == 0) {
            dir = 1;
        }
    }
    public boolean stopped() {
        return dir == 0;
    }
}
