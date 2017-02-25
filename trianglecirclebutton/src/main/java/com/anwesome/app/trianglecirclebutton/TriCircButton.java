package com.anwesome.app.trianglecirclebutton;


import android.graphics.*;

/**
 * Created by anweshmishra on 26/02/17.
 */
public class TriCircButton {
    private float x,y,size,deg = 0;
    private boolean selected = false;
    private TriCircButton() {

    }
    public void setDimensions(float x,float y,float size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }
    public static TriCircButton newInstance(){
        return new TriCircButton();
    }
    public void draw(Canvas canvas,Paint paint) {
        paint.setColor(Color.parseColor("#E0E0E0"));
        float x[] = {-size/2,size/2,0},y[] = {(float)Math.sqrt(3)*size/4,(float)Math.sqrt(3)*size/4,-(float)Math.sqrt(3)*size/4};
        canvas.save();
        canvas.translate(this.x,this.y);
        canvas.rotate(deg);
        for(int i=0;i<3;i++) {
            canvas.drawCircle(x[i],y[i],size/8,paint);
        }
        canvas.restore();
        if(selected) {
            float lineY = y[0]+this.y+size/7;
            canvas.drawLine(0,lineY,size,lineY,paint);
        }
    }
    public boolean stopped() {
        boolean condition = deg%120 == 0;
        if(condition) {
            deg -= deg%120;
        }
        return condition;
    }
    public boolean handleTap(float x,float y) {
        float h = (float)Math.sqrt(3)*size/2;
        boolean condition = x>=this.x-size/2 && x<=this.x+size/2 && y>=this.y-h/2 && y<=this.y+size/2;
        if(condition) {
            selected = true;
        }
        return condition;
    }
    public void update() {
        deg+=24;
    }
    public int hashCode() {
        return (int)x+(int)deg;
    }
}
