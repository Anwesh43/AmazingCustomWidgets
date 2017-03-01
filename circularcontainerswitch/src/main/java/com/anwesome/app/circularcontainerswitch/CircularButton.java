package com.anwesome.app.circularcontainerswitch;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 01/03/17.
 */
public class CircularButton {
    private Bitmap bitmap;
    private char tag;
    private int render = 0;
    private float deg = 0;
    private CircularButton(Bitmap bitmap,char tag) {
        this.bitmap = bitmap;
        this.tag = tag;
    }
    public void setDeg(float deg) {
        this.deg = deg;
    }
    public void draw(Canvas canvas, Paint paint) {
        if(render == 0) {
            bitmap = Bitmap.createScaledBitmap(bitmap,canvas.getWidth()/9,canvas.getWidth()/9,true);
        }
        canvas.save();
        canvas.translate(canvas.getWidth()/3,0);
        canvas.rotate(deg);
        canvas.drawBitmap(bitmap,0,0,paint);
        canvas.restore();
        render++;
    }
    public float getDeg() {
        return deg;
    }
    public int hashCode() {
        return (int)deg+tag+bitmap.hashCode();
    }
}
