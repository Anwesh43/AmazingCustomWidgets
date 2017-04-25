package com.anwesome.ui.directionbutton.controller;

/**
 * Created by anweshmishra on 25/04/17.
 */
public class MovementController {
    private float dir = 0,deg = 0;
    public void move() {
        deg+=dir*20;
        if(deg>=240 || deg<=0) {
            dir = 0;
        }
    }
    public void startMoving() {
        dir = deg == 0?1:-1;
    }
    public boolean stopped() {
        return dir == 0;
    }
    public float getDeg() {
        return deg;
    }
}
