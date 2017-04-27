package com.anwesome.ui.buttongroup;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 27/04/17.
 */
public class ButtonView extends View {
    private int color;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private List<ButtonElement> buttonElements = new ArrayList<>();
    private int time = 0;
    public ButtonView(Context context,int color,List<ButtonElement> buttonElements) {
        super(context);
        this.color = color;
        this.buttonElements = buttonElements;
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            float gap = canvas.getHeight()/(2*buttonElements.size()+1), x = canvas.getWidth()/2, y = 3*gap/2;
            for(ButtonElement buttonElement:buttonElements) {
                buttonElement.setDimension(x,y,4*canvas.getWidth()/5,gap);
                y += 2*gap;
            }
        }
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
