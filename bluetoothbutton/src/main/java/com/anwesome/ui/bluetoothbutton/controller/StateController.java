package com.anwesome.ui.bluetoothbutton.controller;

/**
 * Created by anweshmishra on 14/04/17.
 */
public class StateController {
    public float deg = 0,dir = 0;
    public float getDeg() {
        return deg;
    }
    public void move() {
        deg+=dir*18;
        if(deg>=90 || deg<=0) {
            dir = 0;
        }
    }
    public void startMoving() {
        dir = deg == 0?1:-1;
    }
    public boolean stop() {
        return dir == 0;
    }
}
