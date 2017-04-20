package com.anwesome.ui.clockbutton;

import android.graphics.*;
import com.anwesome.ui.clockbutton.controllers.ClockMovementController;
import com.anwesome.ui.clockbutton.utils.ClockDrawingUtil;

/**
 * Created by anweshmishra on 20/04/17.
 */
public class ClockShape {
    private ClockMovementController clockMovementController = new ClockMovementController();
    private int color = Color.parseColor("#009688");
    private int m=0,h=0;
    private ClockListener clockListener;
    public void setClockListener(ClockListener clockListener) {
        this.clockListener = clockListener;
    }
    public String getTimeString() {
        return  (h>=10)?""+h:"0"+h+":"+((m>=10)?""+m:"0"+m);
    }
    public void setColor(int color) {
        this.color = color;
    }
    public void draw(Canvas canvas, Paint paint,float size) {
        ClockDrawingUtil.drawClock(canvas,paint,size,clockMovementController.getDeg(),color);
    }
    public void move() {
        clockMovementController.move();
        h = (int)(12*(((clockMovementController.getDeg()/12)%360)/360));
        m = (int)(60*((clockMovementController.getDeg()%360)/360));
        if(clockListener!=null) {
            clockListener.onProgress(h,m);
        }
    }
    public void startMoving() {
        clockMovementController.startMoving();
    }
    public boolean stopped() {
        boolean condition =  clockMovementController.stopped();
        if(condition && clockListener!=null) {
            clockListener.onCompletion(h,m);
        }
        return condition;
    }
}
