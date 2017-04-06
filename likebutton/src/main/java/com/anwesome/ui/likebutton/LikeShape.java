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
        canvas.drawRect(new RectF(-size/2,-size/2,-size/2+size/10,size/2),paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GRAY);
        Path path = new Path();
        path.moveTo(-size/2+size/5,-size/2);
        path.lineTo(0,-3*size/4);
        path.lineTo(size/20,-3*size/4+size/20);
        path.lineTo(size/20,-size/2);
        path.lineTo(size/2-size/20,-size/2);
        path.lineTo(size/2-size/20,-size/2+size/20);
        path.lineTo(size/2-size/10,size/2);
        path.lineTo(-size/2+size/5,size/2);
        canvas.drawPath(path,paint);
        canvas.restore();
    }
    public void update() {
        deg+=dir*20;
        if(deg>=180 || deg<=0) {
            dir = 0;
        }
    }
    public void startMoving() {
        dir = deg == 180?1:-1;
    }
    public boolean stop() {
        return dir == 0;
    }
}
