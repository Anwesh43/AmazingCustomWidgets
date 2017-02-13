package com.anwesome.app.menuexpander;

import android.graphics.*;

/**
 * Created by anweshmishra on 13/02/17.
 */
public class Menu {
    private Bitmap bitmap;
    private float x,y,r,scale = 0.7f,dir =0;
    public Menu(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
    public void setDimensions(float x,float y,int r) {
        bitmap = Bitmap.createScaledBitmap(bitmap,r,r,true);
        this.x = x;
        this.y = y;
        this.r = r;
    }
    public void draw(Canvas canvas,Paint paint) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        canvas.save();
        canvas.translate(x+r,y+r);
        canvas.scale(scale,scale);
        canvas.drawCircle(0,0,r,paint);
        canvas.restore();
    }
}
