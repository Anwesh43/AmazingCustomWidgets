package com.anwesome.games.breakablebutton;

import android.graphics.*;

/**
 * Created by anweshmishra on 23/03/17.
 */
public class BreakableButtonController {
    private String text;
    private TextPart textParts[] = new TextPart[2];
    private float w,h,x,y,dir = 0,deg=0,time=0;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public BreakableButtonController(String text,float w) {
        this.w = w/2;
        this.h = w/4;
        this.x = w/2;
        this.y = w/2;
        initTextParts();
    }
    public void initTextParts() {
        paint.setTextSize(h/6);
        String msg = "";
        for(char letter:text.toCharArray()) {
            msg = msg+letter;
            if(paint.measureText(msg) > paint.measureText(text)/2) {
                textParts[0] = new TextPart(msg,w/2-paint.measureText(msg));
                msg = ""+letter;
            }
        }
        textParts[1] = new TextPart(msg,w/2);
    }
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.translate(x,y);
        for(int i=0;i<2;i++) {
            canvas.save();
            canvas.rotate((1-2*i)*deg);
            canvas.drawRoundRect(new RectF(-h/2,0,w/2,h/2),w/8,w/16,paint);
            textParts[i].draw(canvas,paint);
            canvas.restore();
        }
        canvas.restore();
    }
    public void update() {
        if(deg == 45 && time<10) {
            time+=dir;
            if(time == 10) {
                dir = -1;
            }
        }
        else {
            deg+=dir*9;
            if(deg>=45) {
                deg = 45;
            }
            if(deg <= 0) {
                deg = 0;
                dir = 0;
            }
        }


    }
    public void startMoving() {
        if(dir == 0) {
            dir = 1;
        }
    }
    public static BreakableButtonController getInstance(String text,float w) {
        return new BreakableButtonController(text,w);
    }
    public int hashCode() {
        return (int)(x+y+w+h+dir+deg)+text.hashCode();
    }
    private class TextPart {
        private String text;
        private float x;
        public TextPart(String text,float x) {
            this.x = x;
            this.text = text;
        }
        public void draw(Canvas canvas,Paint paint) {
            paint.setColor(Color.WHITE);
            canvas.drawText(text,x,0,paint);
        }
    }
}
