package com.anwesome.ui.clockbutton.controllers;

/**
 * Created by anweshmishra on 20/04/17.
 */
public class ClockMovementController {
    private float deg = 0,dir = 0;
    public float getDeg() {
        return deg;
    }
    public void move() {
        deg+=dir*10;
        if(deg%360 == 0) {
            dir = 0;
        }
    }
    public void startMoving() {
        dir = dir == 0?1:dir;
    }
    public boolean stopped() {
        return dir == 0;
    }
}
