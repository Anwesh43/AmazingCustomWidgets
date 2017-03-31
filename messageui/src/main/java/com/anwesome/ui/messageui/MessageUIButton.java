package com.anwesome.ui.messageui;

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
 * Created by anweshmishra on 01/04/17.
 */
public class MessageUIButton {
    private Activity activity;
    private MessageUIButtonView mView;
    private MessageUi messageUi;
    private View.OnClickListener onClickListener;
    public MessageUIButton(Activity activity) {
        this.activity = activity;
    }
    public void show(int x,int y) {
        if(mView == null) {
            mView = new MessageUIButtonView(activity);
            Point dimension = DimensionsUtil.getDeviceDimension(activity);
            int w = dimension.x;
            activity.addContentView(mView,new ViewGroup.LayoutParams(w/3,w/3));
            messageUi = new MessageUi(w/6,w/6,w/6);
        }
        mView.setX(x);
        mView.setY(y);
    }
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
    private class MessageUIButtonView extends View {
        private boolean isAnimated = false;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public MessageUIButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            messageUi.draw(canvas,paint);
            if(isAnimated) {
                messageUi.update();
                if(messageUi.stopped()) {
                    isAnimated = false;
                    if(onClickListener!=null) {
                        onClickListener.onClick(this);
                    }
                }
                try {
                    Thread.sleep(100);
                    invalidate();
                }
                catch (Exception ex) {

                }
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated) {
                messageUi.startMoving();
                isAnimated = true;
                postInvalidate();
            }
            return true;
        }
    }
}
