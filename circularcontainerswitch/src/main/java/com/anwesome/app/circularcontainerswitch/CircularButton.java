package com.anwesome.app.circularcontainerswitch;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by anweshmishra on 01/03/17.
 */
public class CircularButton {
    private Bitmap bitmap;
    private char tag;
    private boolean selected = false;
    private int render = 0;
    private float deg = 0;
    private CircularButton(Bitmap bitmap,char tag) {
        this.bitmap = bitmap;
        this.tag = tag;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public boolean isSelected() {
        return selected;
    }
    public void setDeg(float deg) {
        this.deg = deg;
    }
    public void draw(Canvas canvas, Paint paint) {
        if(render == 0) {
            bitmap = Bitmap.createScaledBitmap(bitmap,canvas.getWidth()/9,canvas.getWidth()/9,true);
        }
        Path path = new Path();
        path.addCircle(canvas.getWidth()/3,0,canvas.getWidth()/18,Path.Direction.CCW);
        canvas.clipPath(path);
        canvas.drawBitmap(bitmap,5*canvas.getWidth()/18,-canvas.getWidth()/18,paint);
        if(selected) {
            paint.setColor(Color.parseColor("#AAFF0000"));
            canvas.drawPath(path, paint);
        }
        render++;
    }
    public float getDeg() {
        return deg;
    }
    public static CircularButton newInstance(Bitmap bitmap,char tag) {
        return new CircularButton(bitmap,tag);
    }
    public int hashCode() {
        return (int)deg+tag+bitmap.hashCode();
    }
}
