package com.anwesome.app.trianglecirclebutton;


import android.graphics.*;

/**
 * Created by anweshmishra on 26/02/17.
 */
public class TriCircButton {
    private float x,y,size,deg = 0;
    private OnSelectedListener onSelectedListener;
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
    public void unselect() {
        selected = false;
    }
    public void draw(Canvas canvas,Paint paint) {
        paint.setColor(Color.parseColor("#BDBDBD"));
        float x[] = {-size/2,size/2,0},y[] = {(float)Math.sqrt(3)*size/4,(float)Math.sqrt(3)*size/4,-(float)Math.sqrt(3)*size/4};
        canvas.save();
        canvas.translate(this.x,this.y);
        canvas.rotate(deg);
        for(int i=0;i<3;i++) {
            canvas.drawCircle(x[i],y[i],size/2,paint);
        }
        canvas.restore();
        if(selected) {
            paint.setStrokeWidth(size/15);
            float lineY = y[0]+this.y+size/2;
            canvas.drawLine(this.x-size/2,lineY,this.x+size/2,lineY,paint);
        }
    }
    public boolean stopped() {
        boolean condition = deg%360 == 0;
        if(condition) {
            deg -= deg%360;
            if(onSelectedListener!=null) {
                onSelectedListener.onSelected();
            }
        }
        return condition;
    }
    public boolean handleTap(float x,float y) {
        float h = (float)(Math.sqrt(3)*size/2);
        boolean condition = x>=this.x-size/2 && x<=this.x+size/2 && y>=this.y-h/2 && y<=this.y+h/2;
        if(condition) {
            selected = true;
        }
        return condition;
    }
    public void update() {
        deg+=72;
    }
    public int hashCode() {
        return (int)x+(int)deg;
    }
    public void setOnSelectedListener(OnSelectedListener onSelectedListener) {
        this.onSelectedListener = onSelectedListener;
    }
}
