package com.anwesome.ui.ninesqaure;

/**
 * Created by anweshmishra on 17/04/17.
 */
public class StateController {
    private float deg = 0,dir = 0,gap=0,maxGap = 0;
    public StateController(float maxGap) {
        this.gap = maxGap;
        this.maxGap = maxGap;
    }
    public float getGap() {
        return gap;
    }
    public float getDeg() {
        return deg;
    }
    public void move() {
        deg += dir * 18;
        gap -= dir * maxGap / 5;
        if ((deg >= 90 && gap <= 0)) {
            dir = 0;
            deg = 90;
            gap = 0;
        }
        if (gap >= maxGap && deg <= 0) {
            dir = 0;
            deg = 0;
            gap = maxGap;
        }
    }
    public boolean opened() {
        return dir == 0 && deg<=0;
    }
    public void startMoving() {
        dir = deg == 0?1:-1;
    }
    public boolean stopped() {
        return dir == 0;
    }
}
