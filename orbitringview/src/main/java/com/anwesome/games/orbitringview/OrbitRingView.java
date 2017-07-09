package com.anwesome.games.orbitringview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 10/07/17.
 */

public class OrbitRingView extends View {
    private int n=3;
    private Renderer renderer = new Renderer();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public OrbitRingView(Context context) {
        super(context);
    }
    public void setN(int n) {
        this.n = Math.max(this.n,n);
    }
    public void onDraw(Canvas canvas) {
        renderer.render(canvas);
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            renderer.handleTap(event.getX(),event.getY());
        }
        return true;
    }
    private class Renderer {
        private int w,h,time = 0;
        public void render(Canvas canvas) {
            if(time == 0) {
                w = canvas.getWidth();
                h = canvas.getHeight();
            }
            time++;
        }
        public void handleTap(float x,float y) {
        }
    }
    private class DrawingService {
        public DrawingService(int w,int h) {

        }
        public void draw(Canvas canvas) {

        }
    }
}
