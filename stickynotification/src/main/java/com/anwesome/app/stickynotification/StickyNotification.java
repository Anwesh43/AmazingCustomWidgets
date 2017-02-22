package com.anwesome.app.stickynotification;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

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
    public StickyNotification(Activity activity,String text,Bitmap bitmap) {
        mActivity = activity;
        this.bitmap = bitmap;
        initNotification(text);
    }
    private void initNotification(String text) {
        Point size = DimensionsUtil.getDeviceDimension(mActivity);
        int w = size.x,h = size.y;
        float textSize = h/30,notifW= 9*w/10,textW = w/20,notifH = h/60,notifX = textW;
        String tokens[] = text.split(" ");
        String msg = "";
        for(String token:tokens) {
            float tw = paint.measureText(msg);
            if(textW+tw>notifW) {
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
        float totalH = notifH*2,notifY = totalH/2+textSize/2;
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
            stickyElementTextContainer = new StickyElementTextContainer(w/20,totalH/2+textSize/2,textElements,9*w/10,(int)notifH);
            closeButton = new CloseButton(w-w/20,notifH/2-w/20,w/20);
            
        }
        paint.setTextSize(textSize);
    }
    public void show() {

    }
    private class StickyNotificationView extends View {
        private boolean isAnimated = true;
        public StickyNotificationView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(isAnimated) {
                try {
                    Thread.sleep(100);
                    invalidate();
                }
                catch (Exception ex) {

                }
            }
        }
        public void update() {
            switch(animationStore.getMode()) {
                case 0:
                    if(stickyElementTextContainer!=null) {
                        stickyElementTextContainer.update();
                        if(stickyElementTextContainer.isStop()) {
                            animationStore.setMode(1);
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
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
