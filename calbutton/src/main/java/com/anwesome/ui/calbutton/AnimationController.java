package com.anwesome.ui.calbutton;

/**
 * Created by anweshmishra on 05/04/17.
 */
public class AnimationController {
    private float deg=0,time = 0;
    private int mode = -1;
    public float getDeg() {
        return deg;
    }
    public void startMoving() {
        mode = mode == -1?0:mode;
    }
    public void animate() {
        switch(mode) {
            case 0:
                deg-=5;
                if(deg<=-30) {
                    mode = 1;
                }
                break;
            case 1:
                time++;
                if(time%10 == 0) {
                    mode = 2;
                }
                break;
            case 2:
                deg+=5;
                if(deg>=0) {
                    mode = -1;
                }
                break;
            default:
                break;
        }
    }
    public boolean stop() {
        return mode == -1;
    }
}
