package com.anwesome.ui.buttongroup;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 27/04/17.
 */
public class ButtonView extends View {
    private int color;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public ButtonView(Context context,int color) {
        super(context);
        this.color = color;
    }
    public void onDraw(Canvas canvas) {

    }
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
