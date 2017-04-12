package com.anwesome.ui.messengerbutton.controller;

/**
 * Created by anweshmishra on 13/04/17.
 */
public class StateController {
    private float deg = 0,dir = 0;
    public float getDeg() {
        return deg;
    }
    public void move() {
        deg+=dir*30;
        if(deg>=360) {
            deg = 0;
            dir = 0;
        }
    }
    public void startMoving() {
        if(dir == 0) {
            dir = 1;
        }
    }
    public boolean stop() {
        return dir == 0;
    }
}
