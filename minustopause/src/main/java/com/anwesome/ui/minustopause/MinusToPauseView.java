package com.anwesome.ui.minustopause;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 13/05/17.
 */
public class MinusToPauseView extends View {
    private MinusToPauseShape shape = new MinusToPauseShape();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private AnimationHandler animationHandler;
    public MinusToPauseView(Context context) {
        super(context);
        animationHandler = new AnimationHandler(this);
    }
    public void onDraw(Canvas canvas) {
        shape.draw(canvas,paint,canvas.getWidth()/2);
    }
    public void update(float factor) {
        shape.update(factor);
        postInvalidate();
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            animationHandler.startAnim();
        }
        return true;
    }
}
