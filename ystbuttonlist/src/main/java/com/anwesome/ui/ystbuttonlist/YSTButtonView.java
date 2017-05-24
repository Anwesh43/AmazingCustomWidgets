package com.anwesome.ui.ystbuttonlist;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 24/05/17.
 */
public class YSTButtonView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public YSTButtonView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {

    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            
        }
        return true;
    }

}
