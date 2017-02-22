package com.anwesome.app.imageclippe;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 22/02/17.
 */
public class ImageClipper {
    private Bitmap bitmap;
    private Activity activity;
    private ImageClipperShape imageClipperShape;
    public ImageClipper(Activity activity,Bitmap bitmap,ImageClipperShape imageClipperShape) {
        this.activity = activity;
        this.bitmap = bitmap;
        this.imageClipperShape = imageClipperShape;
    }
    public void show() {

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
