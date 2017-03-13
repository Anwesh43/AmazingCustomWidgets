package com.anwesome.games.imagebar;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 14/03/17.
 */
public class ButtonHolderBar {
    private float w,h;
    private ImageBarButton imageBarButton;
    private ButtonHolderBar(float w,float h) {
        this.w = w;
        this.h = h;
        imageBarButton = ImageBarButton.getInstance(w-w/8,h/2,w/20);
    }
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.parseColor("#3F51B5"));
        canvas.drawRect(0,0,w,h,paint);
        if(imageBarButton!=null) {
            imageBarButton.draw(canvas, paint);
        }
    }
    public boolean handleTap(float x,float y) {
        if(imageBarButton!=null) {
            return imageBarButton.handleTap(x, y);
        }
        return false;
    }
    public void update() {
        if(imageBarButton!=null) {
            imageBarButton.update();
        }
    }
    public int hashCode() {
        return (int)(w+h)+(imageBarButton!=null?imageBarButton.hashCode():0);
    }
}
