package com.anwesome.ui.mutebutton;

import android.graphics.*;

/**
 * Created by anweshmishra on 11/04/17.
 */
public class MuteButtonShape {
    private float x,y,size;
    private AnimationController animationController;
    public MuteButtonShape(float x,float y,float size) {
        this.x = x;
        this.y = y;
        this.size = size;
        animationController = new AnimationController();
    }
    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(animationController.getDeg());
        DrawingUtil.drawBlackCircle(canvas,paint,size);
        DrawingUtil.drawWhiteArc(canvas,paint,size);
        DrawingUtil.drawEllipse(canvas,paint,size);
        canvas.restore();
    }
    public void update() {
        animationController.move();
    }
    public void handleTap() {
        animationController.startMoving();
    }
    public boolean stop() {
        return animationController.stop();
    }
    public int hashCode() {
        return (int)(x+y+size+animationController.getDeg());
    }
}
