package com.anwesome.games.hamburgbutton;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 18/03/17.
 */
public class HamburgIcon {
    private IconAnimationQueue iconAnimationQueue = new IconAnimationQueue();
    private float x,y,w,n = 3,l,deg = 0;
    private boolean stopped = true;
    private HamburgIcon(float x,float y,float w) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.l = w/2;
        initAnimations();
    }
    public void initAnimations() {
        iconAnimationQueue.addIconAnimation(new IconAnimation() {
            @Override
            public void execute(int dir) {
                l-=dir*(w/16);
            }

            @Override
            public boolean isDone() {
                return (l<=0 || l>=w/2);
            }
            public void setEdgeConditionOnComplete() {
                if(l<=0) {
                    l = 0;
                    n = 2;
                }
                if(l>=w/2) {
                    l = w/2;
                }
            }
        });
        iconAnimationQueue.addIconAnimation(new IconAnimation() {
            @Override
            public void execute(int dir) {
                deg+=dir*9;
            }

            @Override
            public boolean isDone() {
                return (deg>=45||deg<=0);
            }

            @Override
            public void setEdgeConditionOnComplete() {
                if(deg>=45) {
                    deg = 45;
                }
                if(deg<=0) {
                    deg = 0;
                    n = 3;
                }
            }
        });
    }
    public static HamburgIcon getInstance(float x,float y,float w) {
        return new HamburgIcon(x,y,w);
    }
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(12);
        canvas.save();
        canvas.translate(x,y);
        for(int i = 0;i<n;i++) {
            canvas.save();
            canvas.translate(0,(i-1)*l);
            canvas.rotate(deg*(2*i-1));
            canvas.drawLine(-w/2,0,w/2,0,paint);
            canvas.restore();
        }
        canvas.restore();
    }
    public void update() {
        iconAnimationQueue.animate();
        if(iconAnimationQueue.isStopped()) {
            stopped = true;
        }
    }
    public boolean isStopped() {
        return stopped;
    }
    public void handleTap() {
        stopped = false;
        iconAnimationQueue.startAnimating();
    }
    public int hashCode() {
        return (int)(x+y+w+l+deg);
    }
}
