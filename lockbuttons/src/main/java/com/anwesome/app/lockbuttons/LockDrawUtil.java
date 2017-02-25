package com.anwesome.app.lockbuttons;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by anweshmishra on 25/02/17.
 */
public class LockDrawUtil {
    public static void drawObjects(Canvas canvas, Paint paint,Lock lock,LockKey lockKey,LockButtonType lockButtonType,int w,int h) {
        lock.draw(canvas,paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#64B5F6"));
        switch(lockButtonType) {
            case RECTANGLE:
                canvas.drawRect(new RectF(w/-w/3,h/2,w/2+w/3,h/2+w/2),paint);
                break;
            case ROUNDRECTANGLE:
                canvas.drawRoundRect(new RectF(w/2-w/3,h/2,w/2+w/3,h/2+w/2),w/16,w/16,paint);
                break;
            default:
                break;
        }
        paint.setColor(Color.parseColor("#FDD835"));
        lockKey.draw(canvas,paint);
    }
}
