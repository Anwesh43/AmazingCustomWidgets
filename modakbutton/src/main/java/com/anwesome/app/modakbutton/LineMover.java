package com.anwesome.app.modakbutton;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 21/02/17.
 */
public class LineMover {
    private ConcurrentLinkedQueue<MoverDimension> dimensions = new ConcurrentLinkedQueue<>();
    private float pivotX,pivotY,r;
    public LineMover(float x,float y,float r) {
        this.pivotX = x;
        this.pivotY = y;
        this.r = r;
    }
    public boolean shouldStop() {
        return dimensions.size() == 0;
    }
    public boolean shouldStart() {
        return dimensions.size() == 30;
    }
    public void draw(Canvas canvas, Paint paint) {
        paint.setStrokeWidth(paint.getStrokeWidth()+10);
        paint.setColor(Color.GREEN);
        int index = 0;
        Path path = new Path();
        int speed = 3;
        if(dimensions.size() == 30) {
            speed = 30;
        }
        for(MoverDimension dimension:dimensions) {
            if(index == 0) {
                path.moveTo(dimension.point.x,dimension.point.y);
            }
            else {
                path.lineTo(dimension.point.x,dimension.point.y);
            }
            dimension.speed = speed;
            dimension.move();
            if(!dimension.shouldMove) {
                dimensions.remove(dimension);
                if(dimensions.size() == 0) {
                    break;
                }
            }
            index++;
        }
        canvas.drawPath(path,paint);
    }
    public void addDimensions() {
        dimensions.add(new MoverDimension());
    }
    private class MoverDimension {
        public PointF point = new PointF();
        public float deg = -90,speed = 3;
        public boolean shouldMove = true;
        public MoverDimension() {
            point.x = (float)(pivotX+r*Math.cos(deg*Math.PI/180));
            point.y = (float)(pivotY+r*Math.sin(deg*Math.PI/180));

        }
        public void move() {
            if(deg<270 && shouldMove) {
                deg+=speed;
            }
            else {
                deg = -90;
                shouldMove = false;
            }
            point.x = (float)(pivotX+r*Math.cos(deg*Math.PI/180));
            point.y = (float)(pivotX+r*Math.sin(deg*Math.PI/180));
        }
        public int hashCode() {
            return (int)deg+point.hashCode();
        }
    }
}
