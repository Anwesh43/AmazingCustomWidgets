package com.anwesome.ui.gpbutton;

/**
 * Created by anweshmishra on 26/04/17.
 */
public class MovementController {
    private float dir = 0,deg = 0,scale = 0.5f;
    public void move() {
        scale += dir*0.1f;
        deg += dir*18;
        if(deg >= 90 || deg <= 0) {
            dir = 0;
        }
    }
    public void startMoving() {
        dir = deg == 0?1:-1;
    }
    public float getDeg() {
        return deg;
    }
    public float getScale() {
        return scale;
    }
    public boolean stopped() {
        return dir == 0;
    }
}
