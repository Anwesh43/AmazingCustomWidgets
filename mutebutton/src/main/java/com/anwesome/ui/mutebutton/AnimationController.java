package com.anwesome.ui.mutebutton;

/**
 * Created by anweshmishra on 11/04/17.
 */
public class AnimationController {
    public float deg = 0,dir = 0;
    public float getDeg() {
        return deg;
    }
    public boolean stop() {
        return dir == 0;
    }
    public void move() {
        deg+=dir*20;
        checkEdgeCondition();
    }
    private void checkEdgeCondition() {
        if(deg%180 == 0) {
            dir = 0;
        }
    }
    public void startMoving() {
        dir = deg == 0?1:-1;
    }
}
