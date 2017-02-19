package com.anwesome.app.customfloatingactionbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.View;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

import java.util.*;

/**
 * Created by anweshmishra on 19/02/17.
 */
public class CustomFloatingActionButton {
    private Activity activity;
    private float y = 0,x=0,r=10;
    private int color = Color.parseColor("#303F9F");
    private List<ActionIcon> actionIcons = new ArrayList<>();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public void setColor(int color) {
        this.color = color;
    }
    public CustomFloatingActionButton(Activity activity) {
        this.activity = activity;
    }
    public void addActionIcon(ActionIcon actionIcon) {
        actionIcons.add(actionIcon);
    }
    public void show() {
        Point size = DimensionsUtil.getDeviceDimension(activity);
        float w = size.x,h = size.y,y1 = h/10+h/40+h/30;
        x = w/2;
        y = h/20;
        r = h/20;
        for(ActionIcon actionIcon:actionIcons) {
            actionIcon.setDimensions(w/2,y1,h/15,y);
            y1+=h/40+h/30;
        }

    }
    private class MainActionButton {
        private boolean expanded = false;
        private float deg = 0;
        public void draw(Canvas canvas,Paint paint) {
            for(ActionIcon actionIcon:actionIcons) {
                actionIcon.draw(canvas,paint);
            }
            paint.setColor(color);
            canvas.save();
            canvas.translate(x,y);
            canvas.rotate(deg);
            canvas.drawCircle(0,0,r,paint);
            for(int i=0;i<2;i++) {
                canvas.save();
                canvas.rotate(90*i);
                paint.setColor(Color.WHITE);
                canvas.drawLine(-(r*3)/4,0,(r*3)/4,0,paint);
                canvas.restore();
            }
            canvas.restore();
        }
        public void update() {

        }
    }
    private class FloatingActionButtonView extends View {
        public FloatingActionButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
    }

}
