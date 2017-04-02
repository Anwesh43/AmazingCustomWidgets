package com.anwesome.ui.sharebutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

/**
 * Created by anweshmishra on 03/04/17.
 */
public class ShareButton {
    private Activity activity;
    private ShareShape shareShape;
    private ShareButtonView shareButtonView;
    private View.OnClickListener onClickListener;
    public ShareButton(Activity activity) {
        this.activity = activity;
    }
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
    public void show(int x,int y) {
        if(shareButtonView == null) {
            shareButtonView = new ShareButtonView(activity);
            Point dimension = DimensionsUtil.getDeviceDimension(activity);
            int w = dimension.x/2;
            activity.addContentView(shareButtonView,new ViewGroup.LayoutParams(w,w));
            shareShape = new ShareShape(w/4,w/4,w/8);
        }
        shareButtonView.setX(x);
        shareButtonView.setY(y);
    }
    private class ShareButtonView extends View {
        private boolean isAnimated = false;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public ShareButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            shareShape.draw(canvas,paint);
            if(isAnimated) {
                shareShape.update();
                if(shareShape.isStop()) {
                    isAnimated = false;
                    if(onClickListener!=null) {
                        onClickListener.onClick(this);
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
                shareShape.startMoving();
                isAnimated = true;
                postInvalidate();
            }
            return true;
        }
    }
}
