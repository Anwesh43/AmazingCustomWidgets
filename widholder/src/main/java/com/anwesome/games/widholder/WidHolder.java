package com.anwesome.games.widholder;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.*;
/**
 * Created by anweshmishra on 04/03/17.
 */
public class WidHolder {
    private Activity activity;
    private WidHolderView view;
    public WidHolder(Activity activity) {
        this.activity = activity;
    }
    public void show() {
        if(view == null) {
            view = new WidHolderView(activity);
            activity.addContentView(view,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
    }
    private class WidHolderView extends View {
        public WidHolderView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
        public boolean onTouchEvent(MotionEvent event) {

            return true;
        }
    }
}
