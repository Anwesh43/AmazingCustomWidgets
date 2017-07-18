package com.anwesome.games.multiarcview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 18/07/17.
 */

public class MultiArcView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int time = 0,w,h,n=3;
    public void setN(int n) {
        this.n = Math.max(this.n,n);
    }
    public MultiArcView(Context context) {
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
    private class MultiArc {
        public void draw(Canvas canvas, float scale) {
            for(int i=0;i<n;i++) {
                paint.setColor(Color.GRAY);
                drawArc(canvas,paint,1,i);
                paint.setColor(Color.CYAN);
                drawArc(canvas,paint,scale,i);
            }
        }
        private void drawArc(Canvas canvas,Paint paint,float scale,int i) {
            canvas.save();
            canvas.translate(w/2,h/2);
            float size = ((w/2*i)*1.0f)/n;
            canvas.drawArc(new RectF(-size/2,-size/2,size/2,size/2),270,90*scale,false,paint);
            canvas.restore();
        }
    }
}
