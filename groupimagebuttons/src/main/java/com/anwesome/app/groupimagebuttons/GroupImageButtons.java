package com.anwesome.app.groupimagebuttons;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anweshmishra on 16/02/17.
 */
public class GroupImageButtons {
    private Activity activity;
    private GroupImageButtonsView groupImageButtonsView;
    public GroupImageButtons(Activity activity) {
        this.activity = activity;
    }
    public void show(){
        if(groupImageButtonsView == null) {
            groupImageButtonsView = new GroupImageButtonsView(activity);
            activity.addContentView(groupImageButtonsView,new ViewGroup.LayoutParams(400,400));
        }
    }
    private class GroupImageButtonsView extends View {
        private boolean isAnimated = false;
        private float viewW,viewH;
        private int time = 0;
        public GroupImageButtonsView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(time == 0) {
                viewH = canvas.getHeight();
                viewW = canvas.getWidth();
            }
            time++;
            if(isAnimated) {
                try {
                    Thread.sleep(100);
                    invalidate();
                } catch (Exception ex) {

                }
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated) {

            }
            return true;
        }
    }
}
