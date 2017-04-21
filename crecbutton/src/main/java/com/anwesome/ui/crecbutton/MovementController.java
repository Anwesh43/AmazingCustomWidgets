package com.anwesome.ui.crecbutton;

/**
 * Created by anweshmishra on 21/04/17.
 */
public class MovementController {
    private float deg = 0,dir = 0,scale=0;
    public void update() {
        deg+=dir*9;
        scale+=dir*0.1f;
        if(deg>=360) {
            deg = 180;
            dir = -1;
        }
        if(deg<=0) {
            dir = 0;
            deg =0;
            scale = 0;
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
    public float getDeg() {
        return deg;
    }
    public float getScale() {
        return scale;
    }
}
