package com.anwesome.app.mirrorimagebutton;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 28/02/17.
 */
public class MirroImageRenderer {
    private int time = 0,mode = 0;
    private float sy = 0,deg = 0,speed = 0,dir = 0,degSpeed = 0,finalY=0;
    private Bitmap bitmap;
    public MirroImageRenderer(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
    public boolean isStop() {
        return dir == 0 && degSpeed == 0;
    }
    public void render(Canvas canvas, Paint paint) {
        int w = canvas.getWidth(),h = canvas.getHeight();
        if(time == 0) {
            bitmap = Bitmap.createScaledBitmap(bitmap,w/4,w/4,true);
            finalY = w/8;
            speed = finalY/6;
        }
        int l[] = {-1,1};
        canvas.save();
        canvas.translate(w/2,h/2);
        canvas.rotate(deg);
        for(int i=0;i<2;i++) {
            canvas.scale(1,l[i]);
            canvas.save();
            canvas.translate(0,-sy);
            CircularImageUtil.drawImage(canvas,paint,bitmap,0,0,w/8);
            canvas.restore();
        }
        canvas.restore();
        time++;
    }
    public void startMoving() {
        if(deg == 0 && sy == 0) {
            dir = 1;
        }
        else if(deg == 180) {
            degSpeed = 20;
        }
    }
    public void update() {
        sy-=speed*dir;
        deg+=degSpeed;
        if(sy>=finalY && deg == 0) {
            dir = 0;
            sy = finalY;
            degSpeed = 20;
        }
        else if(deg % 180 == 0) {
            degSpeed  = 0;
            if(deg%360 == 0) {
                dir = -1;
            }
        }
        else if(deg == 0 && sy<=0) {
            dir = 0;
        }
    }
}
