package com.anwesome.app.pathtext;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.*;
import java.util.*;

/**
 * Created by anweshmishra on 20/02/17.
 */
public class PathText {
    private Activity activity;
    private String text;
    private PathTextView pathTextView;
    public PathText(Activity activity,String text) {
        this.activity = activity;
        this.text = text;
    }
    public void show(int x,int y) {
        if(pathTextView == null) {
            pathTextView = new PathTextView(activity);
            activity.addContentView(pathTextView,new ViewGroup.LayoutParams(200,200));
        }
        pathTextView.setX(x);
        pathTextView.setY(y);
    }
    private class PathTextView extends View {
        private List<PointF> points = new ArrayList<>();
        private int render = 0;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private int currIndex = 0,maxTimes = 0;
        private boolean isAnimated = true;
        public PathTextView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(render == 0) {
                int w = canvas.getWidth()/2,h = canvas.getHeight()/2;
                initPoints(w,h);
            }

            Path path = new Path();
            if(points.size()>0 && points.size()>=currIndex+20) {
                for (int i = currIndex; i < currIndex + 20; i++) {
                    if(i == currIndex) {
                        path.moveTo(points.get(i).x,points.get(i).y);
                    }
                    else {
                        path.lineTo(points.get(i).x,points.get(i).y);
                    }
                }
            }
            canvas.drawTextOnPath(text,path,0,0,paint);
            render++;
            if(isAnimated) {
                currIndex+=20;
                maxTimes++;
                if(maxTimes == 20) {
                    maxTimes = 0;
                    currIndex = 0;
                    isAnimated = false;
                }
                try {
                    Thread.sleep(100);
                    invalidate();
                }
                catch(Exception ex) {

                }
            }
        }
        private void initPoints(int w,int h) {
            int gap = w/40;
            for(int i =0;i<20;i++) {
                points.add(new PointF(w/2+i*gap,h/2));
            }
            for(int i=0;i<360;i++) {
                float r = Math.min(w,h);
                float x = (float)(w/2+r*Math.cos(i*Math.PI/180)),y = (float)(h/2+r*Math.sin(i*Math.PI/180));
                points.add(new PointF(x,y));
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(!isAnimated && event.getAction() == MotionEvent.ACTION_DOWN) {
                isAnimated = true;
                postInvalidate();
            }
            return true;
        }
    }
}
