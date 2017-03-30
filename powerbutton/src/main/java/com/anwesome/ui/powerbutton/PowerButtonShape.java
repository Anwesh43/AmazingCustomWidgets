package com.anwesome.ui.powerbutton;

import android.graphics.*;

/**
 * Created by anweshmishra on 31/03/17.
 */
public class PowerButtonShape {
    private float x,y,r,deg = 0,dir = 1;
    private PowerButtonListener listener;
    private PowerButtonShape(float x,float y,float r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }
    public void setPowerButtonListener(PowerButtonListener listener) {
        this.listener = listener;
    }
    public static PowerButtonShape getInstance(float x,float y,float r) {
        return new PowerButtonShape(x,y,r);
    }
    public void draw(Canvas canvas, Paint paint) {
        int a = 20;
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#757575"));
        paint.setStrokeWidth(r/10);
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(deg);
        canvas.drawArc(new RectF(-r,-r,r,r),270+a,360-2*a,false,paint);
        canvas.drawLine(0,0,0,-r,paint);
        canvas.restore();
    }
    public void update() {
        deg+=dir*20;
        if(deg%180 == 0) {
            dir = 0;
            if(listener!=null) {
                if (deg == 0) {
                    listener.off();
                }
                if (deg == -180) {
                    listener.on();
                }
            }
        }
    }
    public void startMoving() {
        dir = deg == 0?-1:1;
    }
    public boolean stopped() {
        return dir == 0;
    }
}
