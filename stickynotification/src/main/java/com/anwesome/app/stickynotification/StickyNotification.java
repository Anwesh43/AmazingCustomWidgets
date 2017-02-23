package com.anwesome.app.stickynotification;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 23/02/17.
 */
public class StickyNotification {
    private Activity mActivity;
    private AnimationStore animationStore = new AnimationStore();
    private StickyNotificationView stickyNotificationView;
    private StickyIcon stickyIcon;
    private CloseButton closeButton;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private StickyElementTextContainer stickyElementTextContainer;
    private List<StickyNotificationTextElement> textElements = new ArrayList<>();
    private Bitmap bitmap;
    private float notifH=100,notifW=100,notifY,notifX = 100;
    public StickyNotification(Activity activity,String text,Bitmap bitmap) {
        mActivity = activity;
        this.bitmap = bitmap;
        initNotification(text);
    }
    private void initNotification(String text) {

        Point size = DimensionsUtil.getDeviceDimension(mActivity);
        int w = size.x,h = size.y;
        h = 8*h/10;
        float textSize = h/40,notifW= 9*w/10,textW = w/20,notifH = textSize,notifX = textW;
        paint.setTextSize(textSize);
        String tokens[] = text.split(" ");
        String msg = "";
        for(String token:tokens) {
            float tw = paint.measureText(msg+" "+token);
            if(textW+tw>=notifW) {
                textW = w/10;
                notifH+=textSize;
                msg = token;
            }
            else {
                msg = msg+" "+token;
            }
        }
        msg = "";
        notifH+=textSize*1.5f;
        float totalH = notifH*2,notifY = totalH/2+textSize;
        for(String token:tokens) {

            float tw = paint.measureText(msg);

            if(textW+tw>notifW) {
                textElements.add(new StickyNotificationTextElement(msg,notifX,notifY));
                notifX = w/20;
                notifY+=textSize;
                msg = token;
            }
            else {
                msg = msg+" "+token;
            }
        }
        textElements.add(new StickyNotificationTextElement(msg,notifX,notifY));
        stickyElementTextContainer = new StickyElementTextContainer(0,totalH/2,textElements,9*w/10,(int)notifH);
        closeButton = new CloseButton(5*w/6,notifH-w/20,w/20);
        bitmap = Bitmap.createScaledBitmap(bitmap,(int)(totalH/2*((bitmap.getWidth()*1.0f)/bitmap.getHeight())),(int)totalH/2,true);
        stickyIcon =  StickyIcon.newInstance(bitmap,0,totalH/2,0);
        this.notifH = totalH;
        this.notifW = notifW;
        this.notifY = h-totalH;
        this.notifX  = w/20;

    }
    public void show() {
        if(stickyNotificationView == null) {
            stickyNotificationView = new StickyNotificationView(mActivity);
            mActivity.addContentView(stickyNotificationView,new ViewGroup.LayoutParams((int)notifW,(int)notifH));
            stickyNotificationView.setX(notifX);
            stickyNotificationView.setY(notifY);
        }
    }
    private class StickyNotificationView extends View {
        private boolean isAnimated = false;
        public StickyNotificationView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            closeButton.draw(canvas,paint);
            stickyIcon.draw(canvas,paint);
            stickyElementTextContainer.draw(canvas,paint);
            if(isAnimated) {
                update();
                try {
                    Thread.sleep(50);
                    invalidate();
                }
                catch (Exception ex) {
                    Log.d("ex",ex.toString());
                }
            }
        }
        public void update() {
            switch(animationStore.getMode()) {
                case -1:
                    closeButton.update();
                    if(closeButton.isStop()) {
                        animationStore.setMode(0);
                        stickyElementTextContainer.startMoving();
                    }
                    break;
                case 0:
                    if(stickyElementTextContainer!=null) {
                        stickyElementTextContainer.update();
                        if(stickyElementTextContainer.isStop()) {
                            animationStore.setMode(1);
                            if(stickyIcon!=null) {
                                stickyIcon.startMoving();
                            }
                        }
                    }
                    break;
                case 1:
                    if(stickyIcon!=null) {
                        stickyIcon.update();
                        if(stickyIcon.isStop()) {
                            isAnimated = false;
                            animationStore.setMode(2);
                        }
                    }
                    break;
                case 2:
                    stickyIcon.update();
                    if(stickyIcon.isStop()) {
                        animationStore.setMode(3);
                        if(stickyElementTextContainer!=null) {
                            stickyElementTextContainer.startMoving();
                        }
                    }
                    break;
                case 3:
                    if(stickyElementTextContainer!=null) {
                        stickyElementTextContainer.update();
                        if(stickyElementTextContainer.isStop()) {
                            animationStore.setMode(4);
                            if(closeButton!=null) {
                                closeButton.startMoving();
                            }
                        }
                    }
                    break;
                case 4:
                    if(closeButton!=null) {
                        closeButton.update();
                        if(closeButton.isStop()) {
                            isAnimated = false;
                            animationStore.setMode(-1);
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated) {
                if((animationStore.getMode() == 2 || animationStore.getMode() == -1) && closeButton.handleTap(event.getX(),event.getY())) {
                    closeButton.startMoving();
                    if(animationStore.getMode() == 2) {
                        stickyIcon.startMoving();
                    }
                    isAnimated = true;
                    postInvalidate();
                }
            }
            return true;
        }
    }
}
