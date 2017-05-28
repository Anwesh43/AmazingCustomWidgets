package com.anwesome.ui.tvbutton;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 28/05/17.
 */

public class TVButtonView extends View{
    private int time = 0,w,h;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public TVButtonView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
        }
        int r = w/8;
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(r/3);
        canvas.save();
        canvas.translate(w/2,h/2);
        canvas.drawRoundRect(new RectF(-2*w/5,-2*h/5,2*w/5,2*h/5),r,r,paint);
        Path path = new Path();
        path.moveTo(0,2*w/5);
        path.lineTo(w/10,w/10);
        path.lineTo(-w/10,w/10);
        canvas.drawPath(path,paint);
        canvas.restore();
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {

        }
        return true;
    }
    public void uodate() {
        postInvalidate();
    }
}
