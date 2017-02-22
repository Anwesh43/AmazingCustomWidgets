package com.anwesome.app.imageclippe;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anweshmishra on 22/02/17.
 */
public class ImageClipper {
    private Bitmap bitmap;
    private Activity activity;
    private ImageClipperShape imageClipperShape;
    private ImageClipperView imageClipperView;
    public ImageClipper(Activity activity,Bitmap bitmap,ImageClipperShape imageClipperShape) {
        this.activity = activity;
        this.bitmap = bitmap;
        this.imageClipperShape = imageClipperShape;
    }
    public void show(int x,int y) {
        if(imageClipperView == null) {
            imageClipperView = new ImageClipperView(activity);
            activity.addContentView(imageClipperView,new ViewGroup.LayoutParams(200,200));
        }
        imageClipperView.setX(x);
        imageClipperView.setY(y);
    }
    private class ImageClipperView  extends View {
        private int time = 0;
        private boolean isAnimated = false;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private ImageClipperElement imageClipperElement;
        public ImageClipperView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(time == 0) {
                imageClipperElement = ImageClipperElementFactory.newImageClipperElement(bitmap,imageClipperShape,canvas.getWidth(),canvas.getHeight());
            }
            if(imageClipperElement!=null) {
                imageClipperElement.draw(canvas,paint);
            }
            time++;
            if(isAnimated) {
                imageClipperElement.update();
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
                isAnimated = true;
                postInvalidate();
            }
            return true;
        }
    }
}
