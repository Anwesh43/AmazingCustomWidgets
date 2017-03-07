package com.anwesome.games.circledarrowbutton;

import android.graphics.*;

/**
 * Created by anweshmishra on 08/03/17.
 */
public class CircledArrow {
    private float x,y,radius,lineLength,triSize,deg=0,dir=0,circScale = 0,triScale=0;
    public CircledArrow(float w) {
        this.x = 3*w/16;
        this.y = 7*w/8;
        this.radius = w/8;
        this.lineLength = w/2;
        this.triSize = w/4;
    }
    public void draw(Canvas canvas,Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(deg);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15);
        paint.setColor(Color.GRAY);
        canvas.drawCircle(0,0,radius,paint);
        canvas.drawLine(radius,0,lineLength,0,paint);
        drawTriangle(canvas,paint,lineLength);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        canvas.save();
        canvas.scale(circScale,circScale);
        canvas.drawCircle(0,0,radius,paint);
        canvas.restore();
        canvas.save();
        canvas.translate(lineLength+triSize/2,0);
        canvas.scale(triScale,triScale);
        drawTriangle(canvas,paint,-triSize/2);
        canvas.restore();
        canvas.restore();
    }
    public void startAnimating() {
        if(dir == 0) {
            dir = deg == 0?1:-1;
        }
    }
    private void drawTriangle(Canvas canvas,Paint paint,float l) {
        Path path = new Path();
        path.moveTo(l,triSize/2);
        path.lineTo(l,-triSize/2);
        path.lineTo(l+triSize,0);
        path.lineTo(l,triSize/2);
        canvas.drawPath(path,paint);
    }
    public void update() {
        deg-=dir*9;
        circScale+=dir*0.1f;
        triScale+=dir*0.1f;
        if(circScale>=1 && triScale>=1 && deg<=-90) {
            dir = 0;
            circScale = 1;
            triScale = 1;
            deg = -90;
        }
        if(circScale<=0 && triScale<=0 && deg>=0) {
            deg = 0;
            circScale = 0;
            dir = 0;
            triScale = 0;
        }
    }
    public boolean stopped() {
        return dir == 0;
    }
    public boolean handleTap(float x,float y) {
        return (x>=this.x-radius && x<=this.x+radius && y>=this.y-radius && y<=this.y+radius);
    }
}
