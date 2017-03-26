package com.anwesome.demos.playbackbutton;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

/**
 * Created by anweshmishra on 27/03/17.
 */
public class PlaybackButtonUtil {
    public static void drawShape(Canvas canvas, Paint paint,float r,PlaybackButtonType type) {
        switch(type) {
            case PLAYBACK_CONTROL:
                drawPlaybackControl(canvas,paint,r);
                break;
            case REPLAY:
                drawReplay(canvas,paint,r);
                break;
            default:
                break;
        }
    }
    private static void drawPlaybackControl(Canvas canvas,Paint paint,float r) {
        paint.setStyle(Paint.Style.FILL);
        Path path = new Path();
        path.moveTo(0,r);
        path.lineTo(r,0);
        path.lineTo(0,-r);
        path.lineTo(0,r);
        canvas.drawPath(path,paint);
        paint.setStrokeWidth(r/10);
        canvas.drawLine(r,-r,r,r,paint);
    }
    private static void drawReplay(Canvas canvas,Paint paint,float r) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(r/10);
        canvas.drawArc(new RectF(-r,-r,r,r),-90,270,false,paint);
        paint.setStyle(Paint.Style.FILL);
        Path path = new Path();
        path.moveTo(-r-r/3,0);
        path.lineTo(-r,-r/3);
        path.lineTo(-r+r/3,0);
        canvas.drawPath(path,paint);
    }
}
