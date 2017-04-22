package com.anwesome.ui.uiwindow.controllers;

/**
 * Created by anweshmishra on 22/04/17.
 */
public class MovementController {
    private float deg = 0,dir = 0;
    public float getDeg() {
        return deg;
    }
    public void move() {
        deg+=dir*20;
        if(deg>=180) {
            deg = 180;
            dir = -1;
        }
        if(deg<=0) {
            deg = 0;
            dir = 0;
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
}
