package com.anwesome.app.alphaimageswitch;

import android.graphics.*;
/**
 * Created by anweshmishra on 24/02/17.
 */
public class AlphaImageSwitchButton {
    private Bitmap bitmap;
    private SelectedListner selectedListener;
    private float x,y,size,scaleDir=0,scale =1;
    public AlphaImageSwitchButton(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
    public void startDeactivating() {
        scaleDir = 0.1f;
    }
    public void startActivating() {
        scaleDir = -0.1f;
    }
    public boolean isStopped() {
        return scaleDir == 0;
    }
    public void update() {
        scale+=scaleDir;
        if(scale>=1){
            scaleDir = 0;
        }
        if(scale<=0) {
            scaleDir = 0;
            if(selectedListener!=null) {
                selectedListener.onSelected();
            }
        }
    }
    public void unselect() {
        scale = 1;
    }
    public void setDimension(float x,float y,float size) {
        bitmap = Bitmap.createScaledBitmap(bitmap,(int)size,(int)size,true);
        this.x = x;
        this.y = y;
        this.size = size;
    }
    public void draw(Canvas canvas,Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        Path path = new Path();
        path.addCircle(0,0,size/2, Path.Direction.CCW);
        canvas.clipPath(path);
        paint.setColor(Color.WHITE);
        canvas.drawBitmap(bitmap,-size/2,-size/2,paint);
        canvas.save();
        canvas.scale(scale,scale);
        paint.setColor(Color.parseColor("#AA9E9E9E"));
        canvas.drawPath(path,paint);
        canvas.restore();
        canvas.restore();
    }
    public void setSelectedLisenter(SelectedListner selectedListener) {
        this.selectedListener = selectedListener;
    }
    public int hashCode() {
        return bitmap.hashCode()+(int)x;
    }
    public boolean handleTap(float x,float y) {
        return x>=this.x-size/2 && x<=this.x+size/2 && y>=this.y-size/2 && y<=this.y+size/2;
    }
}
