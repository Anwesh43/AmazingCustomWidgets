package com.anwesome.app.tablikeviews;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.app.Fragment;

/**
 * Created by anweshmishra on 15/02/17.
 */
public class TabElement {
    private Fragment fragment;
    private String title="";
    private float x=0,y=0,w=0,h=0,scale = 0f,deg=0,dir = 0;
    private  boolean animStopped = false;
    public TabElement(Fragment fragment,String title) {
        this.fragment = fragment;
        this.title = title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public float getX() {

        return x;
    }

    public float getY() {
        return y;
    }

    public float getW() {
        return w;
    }

    public float getH() {
        return h;
    }

    public void setDimensions(float x, float y, float w, float h) {
        this.x = x;
        this.w = w;
        this.y = y;
        this.h = h;
    }
    public void setDefault() {
        scale = 1;
        deg = 360;
    }
    public String getTitle() {
        return this.title;
    }
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        canvas.drawRect(new RectF(x,y,x+w,y+h),paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#AA00695C"));
        canvas.save();
        canvas.translate(x+w/2,y+h/2);
        //canvas.rotate(deg);
        canvas.scale(scale,scale);
        canvas.drawRect(new RectF(-w/2,-h/2,w/2,h/2),paint);
        canvas.restore();
        paint.setColor(Color.WHITE);
        canvas.drawText(title,x+w/2-paint.measureText(title)/2,y+h/2+paint.getTextSize()/4,paint);
    }
    public boolean isAnimStopped() {
        return animStopped;
    }
    public void startAnimation() {
        this.animStopped = false;
    }
    public void update() {
        this.scale+=0.2f*dir;
        this.deg+=72*dir;
        if(this.dir == 1 && this.scale>1) {
            this.scale = 1;
            this.deg = 360;
            this.dir = 0;
            animStopped = true;
        }
        else if(this.dir == -1 && this.scale <0) {
            this.dir = 0;
            this.scale = 0;
            this.deg = 0;
            animStopped = true;
        }
    }

    public void setDir(float dir) {
        this.dir = dir;
    }
    public boolean handleTap(float x,float y) {
        return (x>=this.x && x<=this.x+this.w && y>=this.y && y<=this.y+h);
    }
    public int hashCode() {
        return fragment!=null?fragment.hashCode():0+(int)x+(int)y+(int)h+title.hashCode();
    }
}
