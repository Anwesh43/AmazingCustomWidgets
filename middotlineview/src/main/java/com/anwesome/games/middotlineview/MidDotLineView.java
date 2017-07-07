package com.anwesome.games.middotlineview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 08/07/17.
 */

public class MidDotLineView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    ConcurrentLinkedQueue<MidDotLine> midDotLines = new ConcurrentLinkedQueue<>();
    private int time = 0,w,h,size,n = 5;
    public MidDotLineView(Context context) {
        super(context);
    }
    public void setN(int n) {
        this.n = Math.max(n,this.n);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            size = h/(2*n+1);
            for(int i=0;i<n;i++) {
                midDotLines.add(new MidDotLine(i));
            }
        }
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {

        }
        return true;
    }
    private class MidDotLine {
        private float x,y,r,scale = 0,dir = 0;
        private int index = 0;
        public MidDotLine(int index) {
            x = w/2;
            this.index = index;
            this.y = (3*size/2)+index*(2*size);
            this.r = size/2;
        }
        public void draw(Canvas canvas) {
            canvas.save();
            canvas.translate(x,y);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(0,0,r,paint);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawArc(new RectF(-r,-r,r,r),0,360*scale,true,paint);
            for(int i=0;i<2;i++) {
                canvas.save();
                canvas.scale(2*i-1,1);
                canvas.drawLine(r,0,r+(w/3)*scale,0,paint);
                canvas.drawArc(new RectF((r+w/3)-r,-r,r,r),0,360*scale,true,paint);
                canvas.restore();
            }
            canvas.restore();
        }
        public void update() {
            scale += 0.2f*dir;
            if(scale > 1) {
                dir = 0;
                scale = 1;
            }
            if(scale < 0) {
                scale = 0;
                dir = 0;
            }
        }
        public boolean stopped() {
            return dir == 0;
        }
        public int hashCode() {
            return (int)(x+y);
        }
        private void startUpdating() {
            dir = scale >= 1?-1:1;
        }
        public boolean handleTap(float x,float y) {
            boolean condition = x>=this.x -r && x<=this.x+r && y>=this.y-r && y<=this.y+r;
            if(condition) {
                startUpdating();
            }
            return condition;
        }
    }
}
