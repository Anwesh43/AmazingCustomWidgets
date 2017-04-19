package com.anwesome.ui.watchlikebutton;

/**
 * Created by anweshmishra on 19/04/17.
 */
public class WatchMovementController {
    private float dir = 0,deg = 0;
    public void move() {
        deg+=dir*18;
        if(deg>=90 || deg<=0) {
            dir = 0;
        }
    }
    public boolean stopped() {
        return dir == 0;
    }
    public void startMoving() {
        dir = deg == 0?1:-1;
    }
    public float getDeg() {
        return deg;
    }
}
