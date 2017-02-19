package com.anwesome.app.customfloatingactionbutton;


import android.graphics.*;
import android.view.View;

/**
 * Created by anweshmishra on 19/02/17.
 */
public class ActionIcon {
    private Bitmap bitmap;
    private float x,y,w,cy,intialY,dir = 1,scale=0.7f,scaleDir = 0;
    private boolean takenPosition = false;
    public float getScaleDir() {
        return scaleDir;
    }
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
    public void animateOnClick() {
        scale+=scaleDir*0.1f;
        if(scale>=1) {
            scaleDir = -1;
        }
        if(scale<=0.7f) {
            scaleDir = 0;
            scale = 0.7f;
        }
    }
    public void draw(Canvas canvas, Paint paint) {
        float r = w/2;
        canvas.save();
        canvas.translate(x,cy);
        canvas.scale(scale,scale);
        Path path = new Path();
        path.addCircle(0,0,r, Path.Direction.CCW);
        paint.setColor(Color.GRAY);
        canvas.drawPath(path,paint);
        canvas.clipPath(path);
        canvas.drawBitmap(bitmap,-r/2,-r/2,paint);
        canvas.restore();
    }
    public void update() {
        float speed = Math.abs(y-intialY)/5;
        if(!takenPosition) {
            cy += speed * dir;
        }
        if((dir == 1 && cy>=y) || (dir == -1 && cy<=intialY)) {
            if(dir == 1) {
                cy = y;
            }
            else {
                cy = intialY;
            }
            takenPosition = true;
            dir *= -1;
        }
    }
    public void setTakenPosition(boolean takenPosition) {
        this.takenPosition = takenPosition;
    }
    public boolean isTakenPosition() {
        return takenPosition;
    }
    public int hashCode() {
        return bitmap.hashCode()+(int)y;
    }
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
    public boolean handleTap(float x,float y) {
        boolean condition =  x>=this.x-w/2 && x<=this.x+w/2 && y>=this.y-w/2 && y<=this.y+w/2;
        if(condition) {
            scaleDir = 1;
        }
        return condition;
    }
    public void click(View view) {
        if(onClickListener!=null) {
            onClickListener.onClick(view);
        }
    }
}
