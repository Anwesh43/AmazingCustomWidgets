package com.anwesome.app.groupimagebuttons;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.*;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 16/02/17.
 */
public class GroupImageButtons {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Activity activity;
    private GroupImageButtonsView groupImageButtonsView;
    private GroupImageButton currButton,prevButton;
    private int w,h;
    private ConcurrentLinkedQueue<GroupImageButton> imageButtons = new ConcurrentLinkedQueue<>();
    public GroupImageButtons(Activity activity) {
        this.activity = activity;
        initDimensions();
    }
    public void initDimensions(){
        Point size = DimensionsUtil.getDeviceDimension(activity);
        w = size.x;
        h = size.y;
    }
    public void show(){
        if(groupImageButtonsView == null) {
            groupImageButtonsView = new GroupImageButtonsView(activity);
            activity.addContentView(groupImageButtonsView,new ViewGroup.LayoutParams(h/2,h/2));
        }
    }
    public void addImageButton(GroupImageButton groupImageButton) {
        imageButtons.add(groupImageButton);
    }
    private class GroupImageButtonsView extends View {
        private boolean isAnimated = false;
        private float viewW,viewH,buttonsGap,lastX=0,lastY=0,edgeX= 0;
        private int time = 0;
        public GroupImageButtonsView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(time == 0) {
                viewH = canvas.getHeight();
                viewW = canvas.getWidth();
                initImageButtonDimensions();
            }
            for(GroupImageButton imageButton:imageButtons) {
                imageButton.draw(canvas,paint);
                imageButton.update();
            }
            time++;
            if(isAnimated) {
                if(currButton.isStoppedMoving()) {
                    if(prevButton!=null) {
                        prevButton.setResetPosition();
                    }
                    prevButton = currButton;
                    currButton = null;
                    isAnimated = false;
                }
                try {
                    Thread.sleep(100);
                    invalidate();
                } catch (Exception ex) {

                }
            }
        }
        private void initImageButtonDimensions() {
            float y = 0,w = 2*viewW/5,h=w*0.2f,x = 2*w*0.2f;
            buttonsGap = w*0.2f*2;
            for(int i=0;i<imageButtons.size();i++) {
                x+=buttonsGap;
                if(x>viewW) {
                    edgeX = x-buttonsGap;
                    h += buttonsGap;
                    x = buttonsGap;
                }
            }
            x = buttonsGap;
            y = viewH-h;
            for(GroupImageButton imageButton:imageButtons) {
                imageButton.setDimensions(x,y,w,w);
                x+=buttonsGap;
                if(x>viewW) {
                    y += buttonsGap;
                    x = buttonsGap;
                }
            }
            lastX = x-2*buttonsGap;
            lastY = y;
            if(lastX == 0) {
                lastX = viewW - 2*buttonsGap;
                lastY = y - buttonsGap;
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX(),y = event.getY(),prevX=0,prevY=0;
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated ) {
                for(GroupImageButton groupImageButton:imageButtons) {
                    if(prevButton!=null && prevButton==groupImageButton) {
                        continue;
                    }
                    if(groupImageButton.handleTap(x,y) && currButton==null) {
                        isAnimated = true;
                        currButton = groupImageButton;
                        currButton.startAnimating(0,viewW/2,viewH/6,0.1f,72);
                        prevX = currButton.getX();
                        prevY = currButton.getY();
                        break;
                    }
                }
                if(currButton!=null) {
                    for (GroupImageButton groupImageButton : imageButtons) {
                        if(currButton==groupImageButton && prevButton!=null && prevButton==groupImageButton) {
                            continue;
                        }
                        if(groupImageButton.getY()>=prevY) {
                            if(groupImageButton.getY() == prevY && groupImageButton.getX()>prevX) {
                                groupImageButton.startAnimating(1,groupImageButton.getX()-buttonsGap,groupImageButton.getY(),0,0);
                            }
                            else if(groupImageButton.getY()>prevY && groupImageButton.getY()!=buttonsGap/2){
                                if(groupImageButton.getX() == buttonsGap) {
                                    groupImageButton.startAnimating(1,edgeX,groupImageButton.getY()-buttonsGap,0,0);
                                }
                                else {
                                    groupImageButton.startAnimating(1,groupImageButton.getX()-buttonsGap,groupImageButton.getY(),0,0);
                                }
                            }
                        }
                    }
                    if (prevButton != null) {
                        prevButton.startAnimating(0, lastX, lastY, -0.1f, -72);
                    }
                    isAnimated = true;
                    postInvalidate();
                }
            }
            return true;
        }
    }
}
