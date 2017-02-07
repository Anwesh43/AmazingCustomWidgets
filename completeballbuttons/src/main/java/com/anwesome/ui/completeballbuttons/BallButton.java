package com.anwesome.ui.completeballbuttons;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by anweshmishra on 07/02/17.
 */
public class BallButton {
    private char label;
    private float deg;
    private float x,y,r;
    private View.OnClickListener onClickListener;
    private boolean finishAnimating = false;
    private float scale = 0,scaleSpeed = 0.0f,degLabel = 0,degSpeed = 0,r1;
    public void setR(float r) {
        this.r = r;
    }
    public BallButton(char label) {
        this.label = label;
    }
    public boolean hasFinishedAnimating() {
        return finishAnimating;
    }
    public void setSpeed(float dir) {
        finishAnimating = false;
        this.scaleSpeed = 0.1f*dir;
        this.degSpeed = 36*dir;
    }
    public void setDimensions(float deg,float pivotx,float pivoty,float r1,float r) {
        this.deg = deg;
        this.x = pivotx+(float)(r1*Math.cos(deg*Math.PI/180));
        this.y = pivoty+(float)(r1*Math.sin(deg*Math.PI/180));
        this.r = r;
        this.r1 = r1;
        finishAnimating = true;
    }
    public float getDeg() {
        return deg;
    }
    public void draw(Canvas canvas, Paint paint,int color) {
        paint.setColor(Color.parseColor("#9E9E9E"));
        paint.setTextSize(r);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(r1,0,r,paint);
        paint.setStyle(Paint.Style.FILL);
        canvas.save();
        canvas.translate(r1,0);
        canvas.rotate(degLabel);
        canvas.scale(scale,scale);
        paint.setColor(color);
        canvas.drawCircle(0,0,r,paint);
        paint.setColor(Color.WHITE);
        canvas.drawText(""+label,-paint.measureText(""+label)/2,0,paint);
        canvas.restore();
        scale+=scaleSpeed;
        degLabel+=degSpeed;
        if(degLabel>360 && scale>1.0f) {
            scale = 1.0f;
            degLabel = 360;
            degSpeed = 0;
            scaleSpeed = 0;
            finishAnimating = true;
        }
        if(degLabel<0 && scale <0) {
            scale = 0;
            degLabel = 0;
            degSpeed = 0;
            scaleSpeed = 0;
            finishAnimating = true;
        }
    }
    public int hashCode() {
        return (int)x+(int)y+(int)deg+label;
    }
    public boolean handleTap(float x,float y) {
        return x>=this.x-r && x<=this.x+r && y>=this.y-r && y<=this.y+r;
    }
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
