package com.anwesome.app.bargraphbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 02/03/17.
 */
public class BarGraphButton {
    private Activity activity;
    private List<BarGraph> barGraphs = new ArrayList<>();
    private BarGraphView barGraphView = null;
    public BarGraphButton(Activity activity) {
        this.activity = activity;
    }
    public void addBarGraph() {
        barGraphs.add(BarGraph.newInstance());
    }
    public void show() {
        if(barGraphView == null) {
            barGraphView = new BarGraphView(activity);
            activity.addContentView(barGraphView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
    }
    private class BarGraphView extends View {
        private boolean isAnimated = false;
        private int time = 0;
        private BarGraph prevGraph=null,currGraph=null;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public BarGraphView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(time == 0) {
                if(barGraphs.size()>0) {
                    initBarGraphs(canvas.getWidth(),canvas.getHeight());
                }
            }
            for(BarGraph barGraph:barGraphs) {
                barGraph.draw(canvas,paint);
            }
            time++;
            if(isAnimated) {
                if(currGraph!=null) {
                    currGraph.update();
                    if(prevGraph!=null) {
                        prevGraph.update();
                        if(currGraph.stopped() && prevGraph.stopped()) {
                            isAnimated = false;
                        }
                    }
                    if(isAnimated && currGraph.stopped()) {
                        isAnimated = false;
                    }
                }
                try {
                    Thread.sleep(50);
                    invalidate();
                }
                catch (Exception e) {

                }
            }
        }
        public void initBarGraphs(int w,int h) {
            float gap = (2*w)/(3*barGraphs.size()+2),x = gap/2,y = 2*h/3,h1 = h/2;
            for(BarGraph barGraph:barGraphs) {
                barGraph.setDimensions(x,y,gap,h1);
                x+=(3*gap/2);
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX(),y = event.getY();
            if(event.getAction() == MotionEvent.ACTION_DOWN &&!isAnimated) {
                for(BarGraph barGraph:barGraphs) {
                    if(barGraph.handleTap(x,y)) {
                        currGraph = barGraph;
                    }
                }
                if(currGraph != null) {
                    if(prevGraph!=null) {
                        prevGraph.setDir(-1);
                    }
                    currGraph.setDir(1);
                    isAnimated = true;
                    postInvalidate();
                }
            }
            return true;
        }
    }
}
