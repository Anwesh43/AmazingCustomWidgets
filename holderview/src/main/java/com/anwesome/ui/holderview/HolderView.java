package com.anwesome.ui.holderview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 07/06/17.
 */

public class HolderView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int time = 0,w,h;
    public HolderView(Context context) {
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
    public void update(float factor) {
        postInvalidate();
    }
    private class Holder {
        private List<HolderComponent> holderComponents = new ArrayList<>();
        public Holder() {
            holderComponents.add(new HolderCircle(w/4));
            holderComponents.add(new HolderLeg(w/4,1));
            holderComponents.add(new HolderPlate());
            holderComponents.add(new HolderLeg(3*w/4,-1));
            holderComponents.add(new HolderCircle(w/4+w/2));
        }
        public void draw(Canvas canvas) {
            for(HolderComponent holderComponent:holderComponents) {
                holderComponent.draw(canvas);
            }
        }
        public void update(float factor) {
            int index = (int)factor;
            if(index < holderComponents.size()) {
                holderComponents.get((int) factor).update(factor-index);
            }
        }
    }
    private class HolderComponent {
        public void draw(Canvas canvas) {
            paint.setColor(Color.GRAY);
            paint.setStyle(Paint.Style.STROKE);
            drawComponent(canvas,0);
            paint.setColor(Color.BLUE);
            drawComponent(canvas,1);
        }
        protected void drawComponent(Canvas canvas,int a) {

        }
        public void update(float factor) {
        }
    }
    private class HolderCircle extends HolderComponent {
        private float deg = 0;
        private float x;
        public HolderCircle(float x) {
            this.x = x;

        }
        public void drawComponent(Canvas canvas,int a) {
            float y = h/4,r = h/6;
            canvas.save();
            canvas.translate(x,y);
            canvas.drawArc(new RectF(-r,-r,r,r),deg*a,360-deg*a,false,paint);
            canvas.restore();
        }
        public void update(float factor) {
            deg = 360*factor;
        }
        public int hashCode() {
            return (int)(x+deg);
        }
    }
    private class HolderLeg extends HolderComponent {
        private float x,currY = 0,dir;
        public HolderLeg(float x,float dir) {
            this.x = x;
            this.dir = dir;
        }
        public void drawComponent(Canvas canvas,int a) {
            float gapY = 0;
            if(dir == -1) {
                gapY = h/4;
            }
            canvas.save();
            canvas.translate(x,h/4+h/6+gapY);
            canvas.drawLine(0,currY*a,0,h/4*dir,paint);
            canvas.restore();
        }
        public void update(float factor) {
            currY = (h/4*dir)*factor;
        }
        public int hashCode() {
            return (int)(x+currY+dir);
        }
    }
    private class HolderPlate extends HolderComponent {
        private float currX = 0;
        public void drawComponent(Canvas canvas,int a) {
            canvas.save();
            canvas.translate(w/4,2*h/3);
            canvas.drawLine(currX*a,0,w/2,0,paint);
            canvas.restore();
        }
        public void update(float factor) {
            currX = w/2*factor;
        }
        public int hashCode() {
            return (int)(currX);
        }
    }
}
