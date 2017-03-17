package com.anwesome.games.tripathbutton;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 17/03/17.
 */
public class TriPath {
    private float a;
    private List<PointF> vertices = new ArrayList<>();
    public TriPath(float x,float y,float r) {
        this.a = (r*4)/((float)Math.sqrt(3));
        initVertices(x,y,r);
    }
    private void initVertices(float x,float y,float r) {
        int n = 3,deg = -60;
        for(int i=0;i<3;i++) {
            float vertX = x+(float)(r*Math.cos(deg*Math.PI/180)),vertY = (float)(r*Math.sin(deg*Math.PI/180));
            deg+=(360/n);
            vertices.add(new PointF(vertX,vertY));
        }
    }
    public void draw(Canvas canvas, Paint paint) {
        int i=0;
        Path path = new Path();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GREEN);
        for(PointF pointF:vertices) {
            if(i == 0) {
                path.moveTo(pointF.x,pointF.y);
            }
            else {
                path.lineTo(pointF.x,pointF.y);
            }
            i++;
        }
        canvas.drawPath(path,paint);
    }
    public float getA() {
        return a;
    }
    public PointF getVertexAt(int i) {
        return vertices.get(i);
    }
    public int hashCode() {
        return vertices.hashCode()+(int)a;
    }
}
