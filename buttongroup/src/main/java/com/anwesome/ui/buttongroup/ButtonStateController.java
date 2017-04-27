package com.anwesome.ui.buttongroup;

/**
 * Created by anweshmishra on 27/04/17.
 */
public class ButtonStateController {
    private float x,y,w,h,scale = 0,dir = 0;
    public ButtonStateController(float x,float y,float w,float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    public float getX() {
        return  x;
    }
    public float getY() {
        return y;
    }
    public float getW() {
        return w;
    }
    public float getH() {
        return h;
    }
    public float getScale() {
        return scale;
    }
    public void update() {
        scale += 0.2f*dir;
        if(scale > 1) {
            dir = 0;
            scale = 1;
        }
        if(scale<0) {
            dir = 0;
            scale = 0;
        }
    }
    public boolean stopped() {
        return dir == 0;
    }
    public boolean handleTap(float x,float y) {
        boolean condition =  x>=this.x -w/2 && x<=this.x+w/2 && y>=this.y-h/2 && y<=this.y+h/2;
        if(condition) {
            dir = scale <= 0?1:-1;
        }
        return condition;
    }
    public int hashCode() {
        return (int)(y+scale+dir);
    }
}
