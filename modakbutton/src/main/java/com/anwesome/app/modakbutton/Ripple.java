package com.anwesome.app.modakbutton;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 21/02/17.
 */
public class Ripple {
    private float scale = 0,scaleDir = 0;
    public void startScalingUp() {
        scaleDir = 0.25f;
    }
    public boolean stoppedScaling() {
        return scaleDir == 0;
    }
    private Ripple() {

    }
    public void draw(Canvas canvas, Paint paint,float x,float y,float r) {
        paint.setColor(Color.parseColor("#AA00796B"));
        paint.setStyle(Paint.Style.FILL);
        canvas.save();
        canvas.translate(x,y);
        canvas.scale(scale,scale);
        canvas.drawCircle(0,0,r,paint);
        canvas.restore();
    }
    public void update() {
        scale+=scaleDir;
        if(scale>=1.25f) {
            scaleDir = -.25f;
        }
        if(scale<=0) {
            scaleDir = 0;
            scale = 0;
        }
    }
    public static Ripple newInstance() {
        return new Ripple();
    }
}
