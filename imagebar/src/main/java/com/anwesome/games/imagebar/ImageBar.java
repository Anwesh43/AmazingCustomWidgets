package com.anwesome.games.imagebar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

/**
 * Created by anweshmishra on 14/03/17.
 */
public class ImageBar {
    private Activity activity;
    private Bitmap bitmap;
    private ButtonHolderBar bar;
    private ScrollableImageView scrollableImageView;
    private ImageBarView imageBarView;
    public ImageBar(Activity activity, Bitmap bitmap) {
        this.activity = activity;
        this.bitmap = bitmap;
    }
    public void show(int x,int y) {
        if(imageBarView == null) {
            Point size = DimensionsUtil.getDeviceDimension(activity);
            int w = size.x/2;
            scrollableImageView = ScrollableImageView.newInstance(bitmap);
            scrollableImageView.setHeight(w,w);
            bar = ButtonHolderBar.getInstance(w,w/5);
            imageBarView = new ImageBarView(activity);
            activity.addContentView(imageBarView,new ViewGroup.LayoutParams(w,w));
        }
        imageBarView.setX(x);
        imageBarView.setY(y);
    }
    private class ImageBarView extends View{
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private boolean isAnimated = false;
        public ImageBarView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(bar!=null) {
                bar.draw(canvas, paint);
            }
            if(scrollableImageView!=null) {
                scrollableImageView.draw(canvas, paint);
            }
            if(isAnimated) {
                if(bar!=null && scrollableImageView!=null) {
                    bar.update();
                    scrollableImageView.update();
                    if(bar.stopped() && scrollableImageView.stopped()) {
                        isAnimated = false;
                    }
                }
                try {
                    Thread.sleep(50);
                    invalidate();
                }
                catch (Exception ex) {

                }
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated) {
                if(bar.handleTap(event.getX(),event.getY())) {
                    scrollableImageView.startMoving();
                    isAnimated = true;
                    postInvalidate();
                }
            }
            return true;
        }
    }
}
