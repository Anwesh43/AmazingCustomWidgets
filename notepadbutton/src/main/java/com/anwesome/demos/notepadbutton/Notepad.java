package com.anwesome.demos.notepadbutton;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by anweshmishra on 28/03/17.
 */
public class Notepad {
    private float x,y,deg = 0,dir = 0,size;
    private String text;
    private int n = 3;
    private Notepad(float x,float y,float size,String text) throws Exception{
        this.x = x;
        this.y = y;
        this.size = size;
        if(this.text.split(" ").length<10) {
            this.text = text;
            n = Math.max(n,this.text.split(" ").length);
        }
        else {
            throw new Exception("number of words must be less than 10");
        }
    }
    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(deg);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#FFC107"));
        canvas.drawRect(new RectF(-size/2,-size/2,size/2,-size/3),paint);
        paint.setColor(Color.parseColor("#E0E0E0"));
        canvas.drawRect(new RectF(-size/2,-size/3,size/2,size/2),paint);
        float h = (2*size)/3;
        paint.setTextSize(h/2);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(h/40);
        paint.setColor(Color.parseColor("#212121"));
        String tokens[] = text.split(" ");
        for(int i=0;i<n-1;i++) {
            canvas.drawLine(-0.4f*size,(i+1)*h,0.4f*size,(i+1)*h,paint);
            canvas.drawText(tokens[i],-paint.measureText(tokens[i])/2,-paint.getTextSize()/2,paint);
        }
        canvas.restore();
    }
    public void update() {
        this.deg+=dir*9;
        if(this.deg>=45 || this.deg<=0) {
            dir = 0;
        }
    }
    public void startMoving() {
        dir = deg == 0?1:-1;
    }
    public int hashCode() {
        return text.hashCode();
    }
    public boolean stopped() {
        return dir == 0;
    }
    public static Notepad getInstance(float x,float y,float size,String text) throws Exception{
        return new Notepad(x,y,size,text);
    }
}
