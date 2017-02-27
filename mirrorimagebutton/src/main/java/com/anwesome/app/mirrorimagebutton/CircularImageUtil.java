package com.anwesome.app.mirrorimagebutton;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by anweshmishra on 28/02/17.
 */
public class CircularImageUtil {
    public static void drawImage(Canvas canvas, Paint paint,Bitmap bitmap, float x, float y, float r) {
        canvas.save();
        Path path = new Path();
        path.addCircle(x,y,r, Path.Direction.CCW);
        canvas.clipPath(path);
        canvas.drawBitmap(bitmap,x-r,y-r,paint);
        canvas.restore();
    }
}
