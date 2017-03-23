package com.anwesome.games.livestreamingbutton;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.anwesome.games.animationqueue.AnimationQueue;
import com.anwesome.games.animationqueue.ObjectAnimation;

/**
 * Created by anweshmishra on 24/03/17.
 */
public class LiveStreamingButtonController {
    private float x,y,r,r1,r2,a = 0,deg = 0,dir = 0;
    private AnimationQueue animationQueue = new AnimationQueue();
    public LiveStreamingButtonController(float w) {
        this.x = w/2;
        this.y = w/2;
        this.r = w/10;
        this.r1 = w/4;
        this.r2 = w/3;

        animationQueue.addObjectAnimation(new ObjectAnimation() {
            @Override
            public void animate() {
                a+=dir*6;
            }

            @Override
            public boolean isDone() {
                boolean condition = (dir == 1 && a>= 30) || (dir == -1 && a<=0);
                if(condition){
                    if(dir == 1) {
                        a = 30;
                    }
                    else {
                        a = 0;
                    }
                }
                return condition;
            }
            public int hashCode() {
                return  0;
            }
        });
        animationQueue.addObjectAnimation(new ObjectAnimation() {
            @Override
            public void animate() {
                deg+=dir*15;
            }

            @Override
            public boolean isDone() {
                boolean condition = (dir == 1 && deg>=90) || (dir == -1 && deg<=0);
                if(condition) {
                    if(dir==1) {
                        deg = 90;
                    }
                    else {
                        deg = 0;
                    }
                }
                return  condition;
            }
            public int hashCode() {
                return 1;
            }
        });
    }
    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(deg);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0,0,r,paint);
        drawSignals(canvas,paint);
        canvas.restore();
    }
    public void drawSignals(Canvas canvas,Paint paint) {
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        for(int i = 0;i<2;i++) {
            canvas.save();
            canvas.scale(1,(2*i-1));
            canvas.drawArc(new RectF(-r1,-r1,r1,r1),90-a,2*a,false,paint);
            canvas.restore();
        }
    }
    public void update() {
        animationQueue.execute();
        if(animationQueue.stopped()) {
            dir = 0;
        }
    }
    public boolean stopped() {
        return  dir == 0;
    }
    public void startMoving() {
        dir = deg == 0?1:-1;
        animationQueue.setDir(dir);
    }
    public int hashCode() {
        return (int)(x+y+r+r1+r2+deg+dir);
    }

}
