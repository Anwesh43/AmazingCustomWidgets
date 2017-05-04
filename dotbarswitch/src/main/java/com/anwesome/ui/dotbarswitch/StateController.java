package com.anwesome.ui.dotbarswitch;

/**
 * Created by anweshmishra on 04/05/17.
 */
public class StateController {
    private float scale = 0,w = 0,maxW,dir = 0;
    public float getScale() {
        return scale;
    }
    public void setMaxW(float maxW) {
        this.maxW = maxW;
    }
    public float getW() {
        return w;
    }
    public void update() {
        scale+=dir*0.2f;
        w += dir*(maxW)/5;
        dir = scale>1 || scale<0?0:dir;
        if(dir == 0) {
            scale = scale >= 1?1:0;
            w =  w >= maxW ? maxW:0;
        }
    }
    public void start() {
        dir = scale <= 0?1:-1;
    }
    public boolean stopped() {
        return dir == 0;
    }
}
