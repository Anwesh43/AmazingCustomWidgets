package com.anwesome.app.stickynotification;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

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
    public StickyNotification(Activity activity,String text) {
        mActivity = activity;
        initNotification(text);
    }
    private void initNotification(String text) {

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
