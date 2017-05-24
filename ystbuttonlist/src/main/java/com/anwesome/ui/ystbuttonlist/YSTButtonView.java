package com.anwesome.ui.ystbuttonlist;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 24/05/17.
 */
public class YSTButtonView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int time = 0,w,h;
    public YSTButtonView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
        }
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {

        }
        return true;
    }
    private class YSTRect  {
        private float x,y,size,scale = 0;
        public YSTRect() {
            x = w/2;
            y = h/2;
            size = 4*w/5;
        }
        public void draw(Canvas canvas,Paint paint) {
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(size/15);
            canvas.drawRoundRect(new RectF(x,y,x+size,y+size),size/10,size/10,paint);
            canvas.drawLine(x+size/10,y+3*size/5,x+3*size/10,y+3*size/5,paint);
            canvas.drawLine(x+4*size/10,y+3*size/5,x+9*size/10,y+3*size/5,paint);
            canvas.drawLine(x+size/10,y+4*size/5,x+6*size/10,y+4*size/5,paint);
            canvas.drawLine(x+7*size/10,y+4*size/5,x+9*size/10,y+4*size/5,paint);
            paint.setColor(Color.argb(150,0,0,0));
            canvas.save();
            canvas.translate(x,y);
            canvas.scale(scale,scale);
            canvas.drawRoundRect(new RectF(-size/2,-size/2,size/2,size/2),w/10,h/10,paint);
            canvas.restore();
        }
        public boolean handleTap(float x,float y) {
            boolean condition = x>=this.x && x<=this.x+size && y>=this.y && y<=this.y+size;
            return condition;
        }
        public void update(float factor) {
            scale = factor;
        }
    }
}
