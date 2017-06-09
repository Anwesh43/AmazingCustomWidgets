package com.anwesome.ui.holdfiller;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 10/06/17.
 */

public class HoldFillerView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int time = 0,w,h;
    private HoldCircleButton holdCircleButton;
    public HoldFillerView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            holdCircleButton = new HoldCircleButton();
        }
        canvas.drawColor(Color.BLACK);
        holdCircleButton.draw(canvas);
        time++;
    }
    public void update() {
        invalidate();
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN && holdCircleButton != null) {
            if(holdCircleButton.handleTap(event.getX(),event.getY())) {

            }
        }
        if(event.getAction() == MotionEvent.ACTION_UP && holdCircleButton != null) {
            holdCircleButton.setFill(false);
        }
        return true;
    }
    private class HoldCircleButton {
        private float x,y,r;
        private boolean fill = false;
        public HoldCircleButton() {
            x = w/2;
            y = h/10;
            r = h/20;
        }
        public void setFill(boolean fill) {
            this.fill = fill;
        }
        public void draw(Canvas canvas) {
            paint.setColor(Color.YELLOW);
            paint.setStrokeWidth(r/10);
            if(!fill) {
                paint.setStyle(Paint.Style.STROKE);
            }
            else {
                paint.setStyle(Paint.Style.FILL);
            }
            canvas.drawCircle(x,y,r,paint);
        }
        public boolean handleTap(float x,float y) {
            boolean condition =  x>=this.x-r && x<=this.x+r && y>=this.y-r && y<=this.y+r;
            if(condition) {
                setFill(true);
            }
            return condition;
        }
    }
}
