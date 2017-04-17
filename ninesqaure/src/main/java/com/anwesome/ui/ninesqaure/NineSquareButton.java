package com.anwesome.ui.ninesqaure;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 17/04/17.
 */
public class NineSquareButton {
    private float x,y,w;
    public NineSquareButton(float w) {
        this.x = w/2;
        this.y = w/2;
        this.w = w/2;
    }
    public void draw(Canvas canvas, Paint paint) {

    }
    public void move() {

    }
    public void startMoving() {

    }
    public boolean stopped() {
        return false;
    }
}
