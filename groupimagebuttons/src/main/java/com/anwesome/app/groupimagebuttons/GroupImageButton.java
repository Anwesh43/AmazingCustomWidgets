package com.anwesome.app.groupimagebuttons;

import android.graphics.*;

/**
 * Created by anweshmishra on 16/02/17.
 */
public class GroupImageButton {
    private Bitmap bitmap;
    public float x,y,w,h,scale = 0.2f,deg = 0,xdir = 0,ydir = 0,finalX,finalY;
    public GroupImageButton(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
    public void setDimensions(float x,float y,float w,float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.finalX = x;
        this.finalY = y;

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
        
    }
}
