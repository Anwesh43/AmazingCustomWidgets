package com.anwesome.app.bargraphbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
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
        public BarGraphView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(isAnimated) {
                try {
                    Thread.sleep(50);
                    invalidate();
                }
                catch (Exception e) {

                }
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX(),y = event.getY();
            if(event.getAction() == MotionEvent.ACTION_DOWN &&!isAnimated) {

            }
            return true;
        }
    }
}
