package com.anwesome.app.leaninputcontainer;

/**
 * Created by anweshmishra on 27/02/17.
 */
public class LeanKeyboard {

    private char currChar;
    private float x,y,w,h;
    private LeanKeyboard(float x,float y,float w,float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        initDimensions();
    }
    public static LeanKeyboard newInstance(float x,float y,float w,float h) {
        return new LeanKeyboard(x,y,w,h);
    }
    public void initDimensions() {

    }
}
