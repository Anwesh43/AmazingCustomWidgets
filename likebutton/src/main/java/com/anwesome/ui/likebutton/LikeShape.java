package com.anwesome.ui.likebutton;

import android.graphics.*;

/**
 * Created by anweshmishra on 07/04/17.
 */
public class LikeShape {
    private float x,y,deg = 0,size,dir = 0;
    public LikeShape(float x,float y,float size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }
    public void draw(Canvas canvas,Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(deg);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GRAY);
        canvas.drawRect(new RectF(-size/2,-size/3,-size/2+size/10,size/3),paint);
        Path path = new Path();
        path.moveTo(-size/2+size/5,-size/3);
        path.lineTo(0,-2*size/3);
        path.lineTo(size/20,-2*size/3+size/10);
        path.lineTo(size/20,-size/3);
        path.lineTo(size/2-size/10,-size/3);
        path.lineTo(size/2,-size/3+size/10);
        path.lineTo(size/2,size/3);
        path.lineTo(size/2-size/10,size/3);
        path.lineTo(-size/2+size/5,size/3);
        canvas.drawPath(path,paint);
        canvas.restore();
    }
    public void update() {
        deg-=dir*20;
        if(deg<=-180 || deg>=0) {
            dir = 0;
        }
    }
    public void startMoving() {
        dir = deg == 0?1:-1;
    }
    public boolean stop() {
        return dir == 0;
    }
}
