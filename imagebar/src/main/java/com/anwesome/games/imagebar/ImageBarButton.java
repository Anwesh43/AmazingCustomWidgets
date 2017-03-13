package com.anwesome.games.imagebar;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by anweshmishra on 14/03/17.
 */
public class ImageBarButton {
    private float x,y,size,deg = 0,dir = 0;
    private ImageBarButton(float x,float y,float size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }
    public static ImageBarButton getInstance(float x,float y,float size) {
        return new ImageBarButton(x,y,size);
    }
    public boolean stopped() {
        return dir == 0;
    }
    public boolean handleTap(float x,float y) {
        boolean condition = x>=this.x-this.size/2 && x<=this.x+this.size/2 && y>=this.y-size/2 && y<=this.y+size/2;
        dir = deg == 0?-1:1;
        return condition;
    }
    public int hashCode() {
        return (int)(x+y+size+deg+dir);
    }
    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(deg);
        paint.setColor(Color.BLACK);
        Path path = new Path();
        path.moveTo(-size/2,-size/2);
        path.lineTo(size/2,-size/2);
        path.lineTo(0,size/2);
        path.lineTo(-size/2,-size/2);
        canvas.drawPath(path,paint);
        canvas.restore();
    }
    public void update() {
        deg+=dir*18;
        if(deg%180 == 0) {
            dir = 0;
        }
    }
}
