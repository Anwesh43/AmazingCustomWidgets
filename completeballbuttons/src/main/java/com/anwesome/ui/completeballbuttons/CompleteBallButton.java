package com.anwesome.ui.completeballbuttons;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 07/02/17.
 */
public class CompleteBallButton {
    private Activity activity;
    private int color = Color.parseColor("#FF5722");
    private List<BallButton> ballButtons = new ArrayList<>();
    int w,h,n=0;
    public void setColor(int color) {
        this.color = color;
    }
    public CompleteBallButton(Activity activity) {
        this.activity = activity;
        Point size = DimensionsUtil.getDeviceDimension(activity);
        if(size!=null) {
            w = size.x;
            h = size.y;
            Log.d("size",w+","+h);
        }
    }
    public void addBallButton(BallButton ballButton) {
        ballButtons.add(ballButton);
        n++;
    }
    public void show(int x,int y) {
        if(n!=0) {
            CompleteBallView completeBallView = new CompleteBallView(activity);
            completeBallView.setX(x);
            completeBallView.setY(y);
            int dimen = Math.max(w,h);
            activity.addContentView(completeBallView, new ViewGroup.LayoutParams(dimen/3,dimen/3));
        }
    }
    private class CompleteBallView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private boolean isAnimated = false;
        private int time = 0;
        private BallButton prevButton=null,currButton=null;
        public CompleteBallView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            int w = canvas.getWidth(),h = canvas.getHeight();
            float gap_deg = 360/n,deg = gap_deg/2;
            if(time == 0) {
                for(BallButton ballButton:ballButtons) {
                    ballButton.setDimensions(deg,w/2,h/2,9*w/20,w/20);
                    deg += gap_deg;
                }
                paint.setStrokeWidth(w/60);
            }
            for(BallButton ballButton:ballButtons) {
                canvas.save();
                canvas.translate(w/2,h/2);
                canvas.rotate(ballButton.getDeg());
                paint.setColor(Color.parseColor("#9E9E9E"));
                if(currButton!=null && currButton.equals(ballButton)) {
                    paint.setColor(color);
                }
                canvas.drawLine(0,0,4*w/10,0,paint);
                ballButton.draw(canvas,paint,color);
                canvas.restore();
            }
            if(currButton!=null && currButton.hasFinishedAnimating()) {
                isAnimated = false;
            }
            time++;
            if(isAnimated) {
                try {
                    Thread.sleep(50);
                    invalidate();
                } catch (Exception ex) {

                }
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX(),y = event.getY();
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated) {
                for(BallButton ballButton:ballButtons) {
                    if(ballButton.handleTap(x,y) && ballButton!=currButton) {
                        prevButton = currButton;
                        currButton = ballButton;
                        if(prevButton!=null) {
                            prevButton.setSpeed(-1);
                        }
                        currButton.setSpeed(1);
                        isAnimated = true;
                        postInvalidate();
                    }
                }
            }
            return true;
        }
    }
}
