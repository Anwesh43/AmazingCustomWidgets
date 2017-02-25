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
                canvas.drawRect(new RectF(w/2,h/2-w/2,w,h/2+w/2),paint);
                break;
            case ROUNDRECTANGLE:
                canvas.drawRoundRect(new RectF(w/2,h/2-w/2,w,h/2+w/2),w/16,w/16,paint);
                break;
            case CIRCLE:
                canvas.drawCircle(3*w/4,h/2,w/4,paint);
                break;
            default:
                break;
        }
        lockKey.draw(canvas,paint);
    }
}
