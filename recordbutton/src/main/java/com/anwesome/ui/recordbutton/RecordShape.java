package com.anwesome.ui.recordbutton;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by anweshmishra on 10/04/17.
 */
public class RecordShape {
    private float x,y,size,scale=0.5f,dir = 0,deg = 0;
    private OnRecordButtonClickListener onRecordButtonClickListener;
    public RecordShape(float x,float y,float size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }
    public void setOnRecordButtonClickListener(OnRecordButtonClickListener onRecordButtonClickListener) {
        this.onRecordButtonClickListener = onRecordButtonClickListener;
    }
    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(deg);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(size/4);
        paint.setColor(Color.parseColor("#455A64"));
        canvas.drawArc(new RectF(-size/2,-size/2,size/2,size/2),-20,220,false,paint);
        paint.setColor(Color.parseColor("#424242"));
        canvas.drawArc(new RectF(-size/2,-size/2,size/2,size/2),180,180,false,paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#e53935"));
        canvas.save();
        canvas.scale(scale,scale);
        canvas.drawCircle(0,0,size/4,paint);
        canvas.restore();
        canvas.restore();
    }
    public void update() {
        scale += dir*0.1f;
        deg += dir*18;
        if((deg >= 90 && scale>=1) || (deg<=0 && scale<=0.5f)) {
            dir = 0;
            if(onRecordButtonClickListener != null) {
                if(deg>=90) {
                    onRecordButtonClickListener.onSelect();
                }
                else {
                    onRecordButtonClickListener.onUnselect();
                }
            }
        }
    }
    public void startMoving() {
        dir = deg<=0?1:-1;
    }
    public boolean stop() {
        return dir == 0;
    }
}
