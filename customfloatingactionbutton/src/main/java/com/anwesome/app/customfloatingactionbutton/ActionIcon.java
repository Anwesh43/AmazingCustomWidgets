package com.anwesome.app.customfloatingactionbutton;


import android.graphics.*;

/**
 * Created by anweshmishra on 19/02/17.
 */
public class ActionIcon {
    private Bitmap bitmap;
    private float x,y,w;
    public ActionIcon(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
    public void setDimensions(float x,float y,float w) {
        this.x = x;
        this.y = y;
        this.w = w;
        bitmap = Bitmap.createScaledBitmap(bitmap,(int)w/2,(int)w/2,true);
    }
    public void draw(Canvas canvas, Paint paint) {
        float r = w/2;
        Path path = new Path();
        path.addCircle(x,y,r, Path.Direction.CCW);
        paint.setColor(Color.GRAY);
        canvas.drawCircle(x,y,r,paint);
        canvas.clipPath(path);
        canvas.drawBitmap(bitmap,x-r/2,y-r/2,paint);
    }
    public int hashCode() {
        return bitmap.hashCode()+(int)y;
    }
}
