package com.anwesome.app.mirrorimagebutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anweshmishra on 28/02/17.
 */
public class MirrorImageButton {
    private Activity activity;
    private MirrorImageButtonView mirrorImageButtonView;
    private Bitmap bitmap;
    public MirrorImageButton(Activity activity,Bitmap bitmap) {
        this.activity = activity;
        this.bitmap = bitmap;
    }
    public void show(int x,int y) {
        if(mirrorImageButtonView == null) {
            mirrorImageButtonView = new MirrorImageButtonView(activity);
            activity.addContentView(mirrorImageButtonView,new ViewGroup.LayoutParams(400,400));
        }
        mirrorImageButtonView.setX(x);
        mirrorImageButtonView.setY(y);
    }
    private class MirrorImageButtonView extends View {
        private boolean isAnimated = false;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private MirroImageRenderer mirroImageRenderer;
        public MirrorImageButtonView(Context context) {
            super(context);
            mirroImageRenderer = new MirroImageRenderer(bitmap);
        }
        public void onDraw(Canvas canvas) {
            mirroImageRenderer.render(canvas,paint);
            if(isAnimated) {
                mirroImageRenderer.update();
                if(mirroImageRenderer.isStop()) {
                    isAnimated = false;
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
                mirroImageRenderer.startMoving();
                isAnimated = true;
                postInvalidate();
            }
            return true;
        }
    }
}
