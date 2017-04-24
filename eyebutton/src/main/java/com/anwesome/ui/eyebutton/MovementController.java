package com.anwesome.ui.eyebutton;

/**
 * Created by anweshmishra on 24/04/17.
 */
public class MovementController {
    private float deg = 0,dir = 0;
    public void move() {
        deg+=dir*20;
        if(deg>=360) {
            dir = 0;
            deg = 0;
        }
    }
    public boolean stopped() {
        return dir == 0;
    }
    public void startMoving() {
        if(dir == 0) {
            dir = 1;
        }
    }
    public float getDeg() {
        return deg;
    }
}

