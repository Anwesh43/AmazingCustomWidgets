package com.anwesome.ui.fullscreenbutton;

/**
 * Created by anweshmishra on 12/04/17.
 */
public class StateHandler {
    private float deg = 0,dir = 0,scale = 0.5f;
    public void move() {
        deg+=dir*36;
        scale+=0.1f*dir;
        if(deg>=180 || deg<=0) {
            dir = 0;
        }
    }
    public float getDeg() {
        return deg;
    }
    public float getDir() {
        return dir;
    }
    public float getScale() {
        return scale;
    }
    public boolean stop () {
        return dir == 0;
    }
    public void startMoving() {
        dir = deg == 0?1:-1;
    }
}
