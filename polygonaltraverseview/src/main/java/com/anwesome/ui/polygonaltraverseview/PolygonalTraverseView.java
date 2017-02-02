package com.anwesome.ui.polygonaltraverseview;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.*;

/**
 * Created by anweshmishra on 03/02/17.
 */
public class PolygonalTraverseView extends View {
    private int n = 3,j=0;
    private boolean isAnimated = false;
    private int color = Color.parseColor("#3F51B5");
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public PolygonalTraverseView(Context context,int n,int color) {
        super(context);
        this.n = Math.max(n,this.n);
        if(color!=0) {
            this.color = color;
        }
    }
    public PolygonalTraverseView(Context context, AttributeSet attrs) {
        super(context,attrs);
    }
    public void setN(int n) {
        this.n = n;
    }
    public void setColor(int color) {
        this.color = color;
    }
    public void onDraw(Canvas canvas) {
        int w = canvas.getWidth()/2,h = canvas.getHeight()/2;
        int r = Math.min(w,h)/2;
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(color);
        canvas.drawRect(new RectF(0,0,w,h),paint);
        float x = w/2,y = h/2,prevX = x,prevY = y;
        for(int i=0;i<=j;i++) {
            x = w/2+(float)(r*Math.cos(2*i*Math.PI/n));
            y = h/2+(float)(r*Math.sin(2*i*Math.PI/n));
            if(i!=0) {
                canvas.drawLine(prevX,prevY,x,y,paint);
            }
            prevX = x;
            prevY = y;
        }
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x,y,r/5,paint);
        if(isAnimated) {
            j++;
            if(j == n) {
                isAnimated = false;
            }
            try {
                Thread.sleep(150);
                invalidate();
            } catch (Exception ex) {

            }
        }
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated && j== 0) {
            isAnimated = true;
            postInvalidate();
        }
        return true;
    }
}
