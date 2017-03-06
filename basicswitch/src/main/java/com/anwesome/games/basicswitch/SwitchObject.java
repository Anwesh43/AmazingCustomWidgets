package com.anwesome.games.basicswitch;

import android.graphics.*;

/**
 * Created by anweshmishra on 06/03/17.
 */
public class SwitchObject {
    protected float dir = 0;
    protected float x,y,size;
    private OnSelectedListener onSelectedListener;
    public void setDimensions(float x,float y,float size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }
    public SwitchObject(OnSelectedListener onSelectedListener) {
        this.onSelectedListener = onSelectedListener;
    }
    public OnSelectedListener getOnSelectedListener() {
        return this.onSelectedListener;
    }
    public void draw(Canvas canvas,Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        drawObject(canvas,paint);
        canvas.restore();
    }
    protected void drawObject(Canvas canvas,Paint paint) {

    }
    public void update() {

    }
    public void select() {
        dir = 1;
    }
    public void unselect() {
        dir = -1;
    }
    public boolean stoppedAnimating() {
        return dir == 0;
    }
    public int hashCode() {
        return (int)x;
    }
    public boolean handleTap(float x,float y) {
        return x>=this.x-size/2 && x<=this.x+size/2 && y>=this.y-size/2 && y<=this.y+size/2;
    }
}
