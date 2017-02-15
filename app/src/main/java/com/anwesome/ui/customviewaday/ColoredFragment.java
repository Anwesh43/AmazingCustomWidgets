package com.anwesome.ui.customviewaday;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anweshmishra on 15/02/17.
 */
public class ColoredFragment extends View {
    private int color,num;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public ColoredFragment(Context context,int color,int num) {
        super(context);
        this.num = num;
        this.color = color;
    }
    public void onDraw(Canvas canvas) {
        int w = canvas.getWidth()/2,h = canvas.getHeight()/2;
        canvas.drawColor(color);
        paint.setTextSize(w/4);
        canvas.drawText(""+num,w-paint.measureText(""+num)/2,h,paint);
    }
}
