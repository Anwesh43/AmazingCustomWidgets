package com.anwesome.games.multiplecheckbox;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by anweshmishra on 07/03/17.
 */
public class CheckBox {
    private String text = "";
    private float x,y,size,deg=0,scale = 0,dir = 0;
    public static CheckBox newInstance(String text) {
        return new CheckBox(text);
    }
    private CheckBox(String text) {
        this.text = text;
    }
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRoundRect(new RectF(x-size/2,y-size/2,x+size/2,y+size/2),size/10,size/10,paint);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        canvas.save();
        canvas.translate(x,y);
        canvas.scale(scale,scale);
        canvas.rotate(deg);
        canvas.drawCircle(0,0,size/4,paint);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(15);
        canvas.drawLine(-size/4,0,0,size/8,paint);
        canvas.drawLine(0,size/8,size/8,-(float)Math.sqrt(3)*size/8,paint);
        canvas.restore();
    }
    public void update() {
        deg+=36*dir;
        scale+=0.1f*dir;
        if((scale>=1) || (scale<=0)) {
            dir = 0;
        }
    }
    public int hashCode() {
        return text.hashCode()+(int)(x+y+deg+dir+scale);
    }
}
