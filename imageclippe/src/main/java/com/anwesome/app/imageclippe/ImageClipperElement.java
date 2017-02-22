package com.anwesome.app.imageclippe;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by anweshmishra on 22/02/17.
 */
public class ImageClipperElement {
    private Bitmap bitmap;
    private float scale = 1,scaleDir = 0,rot = 0,rotDir = 0;
    private int render = 0;
    private ImageClipperElement(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
    private ImageClipperElement() {

    }
    public static ImageClipperElement getInstance(Bitmap bitmap) {
        return new ImageClipperElement(bitmap);
    }
    public void draw(Canvas canvas, Paint paint) {
        int w = canvas.getWidth(),h = canvas.getHeight();
        Path path = definePath(w,h);
        if(render == 0) {
            bitmap = Bitmap.createScaledBitmap(bitmap,w,h,true);
        }
        if(path!=null) {
            canvas.clipPath(path);
        }
        canvas.save();
        canvas.translate(w/2,h/2);
        canvas.rotate(rot);
        canvas.scale(scale,scale);
        canvas.drawBitmap(bitmap,-w/2,-h/2,paint);
        canvas.restore();
        render++;
    }
    public void update() {
        rot+=rotDir;
        scale+=scaleDir;
        if(scale<0.5f) {
            scaleDir = -1;
            rotDir*=-1;
        }
        if(scale>-1) {
            scaleDir = 0;
            rotDir = 0;
            rot = 0;
            scale = 1;
        }
    }
    protected Path definePath(int w,int h) {
        return null;
    }
}
