package com.anwesome.ui.crukybutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.*;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

/**
 * Created by anweshmishra on 08/02/17.
 */
public class CrukyButton {
    private Activity mActivity;
    private int w,h;
    private int color = Color.parseColor("#43A047");
    private View.OnClickListener onClickListener;
    public CrukyButton(Activity activity) {
        this.mActivity = activity;
        Point size = DimensionsUtil.getDeviceDimension(mActivity);
        if(size!=null) {
            w = size.x;
            h = size.y;
        }
    }
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
    public void show() {
        CrukyButtonView crukyButtonView = new CrukyButtonView(mActivity);
        mActivity.addContentView(crukyButtonView,new ViewGroup.LayoutParams(w/4,h/6));
    }
    private class CrukyButtonView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private int time = 0,cw=100,ch=100,degTime = 0;
        private float deg = 0,dir = 0;
        private boolean shouldAnimate = false;
        private final int xlDir[] = {-1,1,-1,1},ylDir[] = {-1,-1,1,1};
        public CrukyButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(time == 0) {
                cw = canvas.getWidth();
                ch = canvas.getHeight();
            }
            canvas.save();
            canvas.translate(cw/2,ch/2);
            canvas.rotate(deg);
            paint.setColor(color);
            canvas.drawRoundRect(new RectF(-cw/4,-ch/4,cw/4,ch/4),cw/8,ch/8,paint);
            for(int i = 0;i<4;i++) {
                paint.setColor(Color.WHITE);
                canvas.drawCircle(cw/8*xlDir[i],ch/8*ylDir[i],cw/16,paint);
            }
            canvas.restore();
            if(shouldAnimate) {
                deg+=dir*5;
                if(deg >= 45) {
                    deg = 45;
                    dir = 0;
                    degTime++;
                    if(degTime == 5) {
                        dir = -1;
                    }
                }
                else if(deg<=0) {
                    deg = 0;
                    dir = 0;
                    shouldAnimate = false;
                    degTime = 0;
                }
                try {
                    Thread.sleep(20);
                    invalidate();
                }
                catch (Exception ex) {

                }
            }

        }
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX(),y = event.getY();
            if(event.getAction() == MotionEvent.ACTION_DOWN && !shouldAnimate) {
                if(x >= cw/4 && x<=3*cw/4 && y>=ch/4 && y<=3*ch/4) {
                    shouldAnimate = true;
                    dir = 1;
                    postInvalidate();
                }
            }
            return true;
        }
    }
}
