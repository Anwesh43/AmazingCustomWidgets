package com.anwesome.ui.buttongroup;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.List;

/**
 * Created by anweshmishra on 27/04/17.
 */
public class DrawingUtil {
    public static void drawButton(Canvas canvas, Paint paint,String title,ButtonStateController buttonStateController,int color) {
        float x = buttonStateController.getX(),y = buttonStateController.getY(),w = buttonStateController.getW(),h = buttonStateController.getH(),scale = buttonStateController.getScale();
        canvas.save();
        canvas.translate(x,y);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(Math.min(w,h)/10);
        paint.setColor(Color.parseColor("#424242"));
        canvas.drawRoundRect(new RectF(-w/2,-h/2,w/2,h/2),Math.max(w,h)/10,Math.max(w,h)/10,paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize(h/3);
        String adjustedTitle = adjustString(paint,title,7*w/10);
        canvas.drawText(adjustedTitle,-paint.measureText(adjustedTitle)/2,paint.getTextSize()/2,paint);
        canvas.save();
        canvas.scale(scale,scale);
        paint.setColor(color);
        canvas.drawRoundRect(new RectF(-w/2,-h/2,w/2,h/2),Math.max(w,h)/10,Math.max(w,h)/10,paint);
        canvas.restore();
        canvas.restore();
    }
    private static String adjustString(Paint paint,String title,float w) {
        String msg = "";
        for(int i=0;i<title.length();i++) {
            char ch = title.charAt(i);
            if(paint.measureText(msg+ch) < w) {
                msg += ch;
            }
            else {
                msg += "..";
                break;
            }
        }
        return msg;
    }
    public static void drawButtons(List<ButtonElement> buttonElements,Canvas canvas,Paint paint) {
        for(ButtonElement buttonElement:buttonElements) {
            buttonElement.draw(canvas,paint);
        }
    }
}
