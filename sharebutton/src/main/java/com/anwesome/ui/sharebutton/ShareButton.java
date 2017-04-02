package com.anwesome.ui.sharebutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 03/04/17.
 */
public class ShareButton {
    private Activity activity;
    private ShareButtonView shareButtonView;
    public ShareButton(Activity activity) {
        this.activity = activity;
    }
    public void show(int x,int y) {
        if(shareButtonView == null) {
            shareButtonView = new ShareButtonView(activity);
        }
    }
    private class ShareButtonView extends View {
        public ShareButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
