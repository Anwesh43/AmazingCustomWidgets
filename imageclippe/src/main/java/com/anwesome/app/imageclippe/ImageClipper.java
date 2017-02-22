package com.anwesome.app.imageclippe;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 22/02/17.
 */
public class ImageClipper {
    public ImageClipper(Activity activity) {

    }
    public void show() {

    }
    private class ImageClipperView  extends View {
        public ImageClipperView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            
        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
