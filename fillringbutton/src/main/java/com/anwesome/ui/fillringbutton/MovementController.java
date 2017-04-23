package com.anwesome.ui.fillringbutton;

/**
 * Created by anweshmishra on 23/04/17.
 */
public class MovementController {
    private int mode = 0;
    private float l = 0,a = 0,deg = 0,dir = 0,finalL;
    public MovementController(float w) {
        this.finalL = w/Constants.L_SCALE;
    }
    public float getL() {
        return l;
    }
    public float getA() {
        return a;
    }
    public float getDeg() {
        return deg;
    }
    public void update() {
        switch (mode) {
            case 0:
                l+= finalL/5*dir;
                if(l<=0) {
                    l = 0;
                    dir = 0;
                }
                if(l>=finalL) {
                    l = finalL;
                    mode += dir;
                }
                break;
            case 1:
                a+=30*dir;
                if(a<=0 || a>=360) {
                    mode += dir;
                }
                break;
            case 2:
                deg+=dir*18;
                if(deg>=90) {
                    deg = 90;
                    dir = 0;
                }
                if(deg<=0) {
                    mode += dir;
                }
                break;
            default:
                break;
        }
    }
    public void startUpdating() {
        dir = deg == 0?1:-1;
    }
    public boolean stopped() {
        return dir == 0;
    }
}
