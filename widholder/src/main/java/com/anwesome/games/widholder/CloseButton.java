package com.anwesome.games.widholder;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by anweshmishra on 04/03/17.
 */
public class CloseButton {
    private float x,y,r,deg = -90,speed = 18;
    private CloseButton() {

    }
    public static CloseButton newInstance() {
        return new CloseButton();
    }
    public void setDimensions(float x,float y,float r) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.deg = -90;
    }
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        canvas.save();
        canvas.translate(x,y);
        canvas.drawCircle(0,0,r,paint);
        canvas.drawLine(-r/2,-r/2,r/2,r/2,paint);
        canvas.drawLine(r/2,-r/2,-r/2,r/2,paint);
        paint.setColor(Color.GREEN);
        Path path = new Path();
        for(int i=-90;i<=deg;i++) {
            float x = (float)(r*Math.cos(i*Math.PI/180)),y = (float)(r*Math.sin(i*Math.PI/180));
            if(i == -90) {
                path.moveTo(x,y);
            }
            else {
                path.lineTo(x,y);
            }
        }
        canvas.drawPath(path,paint);
        canvas.restore();
    }
    public void update() {
        deg+=speed;
    }
    public boolean handleTap(float x,float y) {
        return x>=this.x-r && x<=this.x+r && y>=this.y-r && y<=this.y+r;
    }
}
