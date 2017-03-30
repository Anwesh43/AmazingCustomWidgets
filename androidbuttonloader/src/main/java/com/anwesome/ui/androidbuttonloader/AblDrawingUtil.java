package com.anwesome.ui.androidbuttonloader;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by anweshmishra on 30/03/17.
 */
public class AblDrawingUtil {
    private static void drawAndroidBody(Canvas canvas, Paint paint,float w,float h) {
        paint.setColor(Color.parseColor("#43A047"));
        canvas.drawRoundRect(new RectF(-w/2,0,w/2,h),w/10,h/10,paint);
    }
    private static void drawAndroidFace(Canvas canvas,Paint paint,float w,float h) {
        paint.setColor(Color.parseColor("#43A047"));
        canvas.drawArc(new RectF(-w/2,-h/2,w/2,h/2),180,180,true,paint);
        paint.setColor(Color.parseColor("#FFFDE7"));
        for(int i = 0;i<2;i++) {
            canvas.save();
            canvas.translate(-w / 4, -h / 4);
            canvas.scale((2*i-1),1);
            canvas.drawCircle(0,0,w/20,paint);
            canvas.restore();
        }
    }
    private static  void drawAndroidHorn(Canvas canvas,Paint paint,float r) {
        paint.setColor(Color.parseColor("#43A047"));
        float newR = r*1.5f;
        for(int i = 0;i<2;i++) {
            float deg = 45*(2*i-1);
            float x1 = (float)(r*Math.cos(deg*Math.PI/180)),y1 = (float)(r*Math.sin(deg*Math.PI/180));
            float x2 = (float)(newR*Math.cos(deg*Math.PI/180)),y2 = (float)(newR*Math.sin(deg*Math.PI/180));
            canvas.drawLine(x1,y1,x2,y2,paint);
        }
    }
    public static void drawAndroidShape(Canvas canvas,Paint paint,float size) {
        drawAndroidBody(canvas,paint,size,size/2);
        drawAndroidFace(canvas,paint,size,size);
        drawAndroidHorn(canvas,paint,size/2);
    }
}
