package com.anwesome.games.ellipticalswitch;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.anwesome.games.basicswitch.OnSelectedListener;
import com.anwesome.games.basicswitch.SwitchObject;

/**
 * Created by anweshmishra on 06/03/17.
 */
public class EllipticalSwitchObject extends SwitchObject {
    private float scale=1,rot = 0;
    public EllipticalSwitchObject(OnSelectedListener onSelectedListener) {
        super(onSelectedListener);
    }
    public void drawObject(Canvas canvas, Paint paint) {
        paint.setColor(Color.parseColor("#00B8D4"));
        canvas.scale(scale,scale);
        canvas.rotate(rot);
        canvas.drawArc(new RectF(-size/2,-size/4,size/2,size/4),0,360,true,paint);
    }
    public void update() {
        scale+=0.1f*dir;
        rot+=18*dir;
        if(scale>=1.5f) {
            dir = 0;
            rot = 90;
            scale = 1.5f;
        }
        if(scale<=1.0f) {
            dir = 0;
            rot = 0;
            scale = 1.0f;
        }
    }
}
