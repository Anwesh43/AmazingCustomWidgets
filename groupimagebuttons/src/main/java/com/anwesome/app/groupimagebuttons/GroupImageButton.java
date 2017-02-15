package com.anwesome.app.groupimagebuttons;

import android.graphics.*;

/**
 * Created by anweshmishra on 16/02/17.
 */
public class GroupImageButton {
    private Bitmap bitmap;
    private int mode = 0;
    public float x,y,w,h,scale = 0.2f,deg = 0,xdir = 0,ydir = 0,finalX,finalY,scaleSpeed=0,degSpeed=0;
    private boolean moving = false;
    public GroupImageButton(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
    public void startAnimating(int mode,float finalX,float finalY,float scaleSpeed,float degSpeed) {
        this.mode = mode;
        this.finalX = finalX;
        this.finalY = finalY;
        this.scaleSpeed = scaleSpeed;
        this.degSpeed = degSpeed;
        this.xdir = (finalX-x)/5;
        this.ydir = (finalY-y)/5;
    }
    public void setDimensions(float x,float y,float w,float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.finalX = x;
        this.finalY = y;
        this.moving = true;
    }
    public boolean isMoving() {
        return moving;
    }
    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(deg);
        canvas.scale(scale,scale);
        Path path = new Path();
        path.addCircle(0,0,w/2, Path.Direction.CCW);
        canvas.clipPath(path);
        canvas.drawBitmap(bitmap,-w/2,-h/2,paint);
        canvas.restore();
    }
    public void update() {
        x+=xdir;
        y+=ydir;
        if(mode == 0) {
            if((ydir<0 && y<finalY) || (ydir>0 && y>finalY)) {
                ydir = 0;
                xdir = 0;
                x = finalX;
                y = finalY;
                degSpeed = 0;
                scaleSpeed = 0;
            }
        }
        else if(mode == 1) {
            if((ydir<0 && y<finalY) || (ydir>0 && y>finalY)) {
                ydir = 0;
                y = finalY;
            }
            if((xdir<0 && x<finalX) || (xdir>0 && x>finalX)) {
                xdir = 0;
                x = finalX;
            }
        }
    }
}
