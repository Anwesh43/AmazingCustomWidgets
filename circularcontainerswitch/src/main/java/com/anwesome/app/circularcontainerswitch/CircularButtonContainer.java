package com.anwesome.app.circularcontainerswitch;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.*;
import java.util.*;

/**
 * Created by anweshmishra on 01/03/17.
 */
public class CircularButtonContainer {
    private Activity activity;
    private float gap;
    private List<CircularButton> circularButtons = new ArrayList<>();
    private CircularButtonContainerView circularButtonContainerView = null;
    public CircularButtonContainer(Activity activity) {
        this.activity = activity;
    }
    public void addCircularButton(Bitmap bitmap,char tag) {
        CircularButton circularButton = CircularButton.newInstance(bitmap,tag);
        circularButtons.add(circularButton);
    }
    public void show() {
        if(circularButtonContainerView == null) {
            if(circularButtons.size() > 0) {
                gap = 360/(circularButtons.size());
                float deg = 0;
                for(CircularButton circularButton:circularButtons) {
                    circularButton.setDeg(deg);
                    deg+=gap;
                }
                circularButtons.get(0).setSelected(true);
            }
            circularButtonContainerView = new CircularButtonContainerView(activity);
            activity.addContentView(circularButtonContainerView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
    }
    private class CircularButtonContainerView extends View {
        public CircularButtonContainerView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
