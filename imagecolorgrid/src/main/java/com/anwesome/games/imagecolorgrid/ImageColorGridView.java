package com.anwesome.games.imagecolorgrid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 22/06/17.
 */

public class ImageColorGridView extends View {
    private int n = 3,time = 0,w,h;
    private int color = Color.parseColor("#0D47A1");
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public ImageColorGridView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
        }
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
    private class ColorGrid {
        private float x,y,size,scale = 0,dir = 0,prevDir = -1;
        public void update() {
            scale+=dir*0.2f;
            if(scale > 1) {
                scale = 1;
                dir = 0;
                prevDir = 1;
            }
            if(scale < 0) {
                dir = 0;
                scale = 0;
                prevDir = -1;
            }
        }
        private void startUpdating() {
            dir = prevDir*-1;;
        }
        public boolean stopped() {
            return dir == 0;
        }
        public ColorGrid(int i) {
            this.size = w/n;
            this.x = size*(i%3)+size/2;
            this.y = (size*(i/3))+size/2;
        }
        public void draw(Canvas canvas) {
            canvas.save();
            canvas.translate(x,y);
            int r = Color.red(Color.GRAY),g = Color.green(Color.GRAY),b = Color.blue(Color.GRAY);
            paint.setColor(Color.argb(150,r,g,b));
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(new RectF(-size/2,-size/2,size/2,size/2),paint);
            canvas.save();
            canvas.scale(scale,scale);
            r = Color.red(color);
            b = Color.green(color);
            b = Color.blue(color);
            paint.setColor(Color.argb(150,r,g,b));
            canvas.drawRect(new RectF(-size/2,-size/2,size/2,size/2),paint);
            canvas.restore();
            canvas.restore();
        }
        public int hashCode() {
            return (int)(x+y);
        }
    }
}
