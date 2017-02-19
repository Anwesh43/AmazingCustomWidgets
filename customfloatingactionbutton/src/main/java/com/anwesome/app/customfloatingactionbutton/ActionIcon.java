package com.anwesome.app.customfloatingactionbutton;


import android.graphics.*;
import android.view.View;

/**
 * Created by anweshmishra on 19/02/17.
 */
public class ActionIcon {
    private Bitmap bitmap;
    private float x,y,w,cy,intialY,dir = 0;
    private View.OnClickListener onClickListener;
    public ActionIcon(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
    public void setDir(float dir) {
        this.dir = dir;
    }
    public void setDimensions(float x,float y,float w,float cy) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.cy = cy;
        this.intialY = cy;
        bitmap = Bitmap.createScaledBitmap(bitmap,(int)w/2,(int)w/2,true);
    }
    public void draw(Canvas canvas, Paint paint) {
        float r = w/2;
        Path path = new Path();
        path.addCircle(x,cy,r, Path.Direction.CCW);
        paint.setColor(Color.GRAY);
        canvas.drawCircle(x,cy,r,paint);
        canvas.clipPath(path);
        canvas.drawBitmap(bitmap,x-r/2,cy-r/2,paint);
    }
    public void update() {
        float speed = Math.abs(y-intialY)/5;
        y+=speed*dir;
        if((dir == 1 && cy>=y) || (dir == -1 && cy<=intialY)) {
            if(dir == 1) {
                cy = y;
            }
            else {
                cy = intialY;
            }
            dir = 0;
        }
    }
    public int hashCode() {
        return bitmap.hashCode()+(int)y;
    }
    public boolean handleTap(float x,float y) {
        return x>=this.x-w/2 && x<=this.x+w/2 && y>=this.y-w/2 && y<=this.y+w/2;
    }
}
