package com.anwesome.ui.fsarrowbutton.controllers;

/**
 * Created by anweshmishra on 15/04/17.
 */
public class StateController {
    private float dir = 0,deg = 0;
    public void move() {
        deg+=20*dir;
        if(deg>=180 || deg<=0) {
            dir = 0;
            if(deg>=180) {
                dir = 0;
            }
            if(deg<=0) {
                dir = 0;
            }
        }
    }
    public float getDeg() {
        return deg;
    }
    public void startMoving() {
        dir = deg == 0?1:-1;
    }
    public boolean stop() {
        return dir == 0;
    }
    public boolean expanded() {
        return deg>=180 || deg<=0;
    }

}
