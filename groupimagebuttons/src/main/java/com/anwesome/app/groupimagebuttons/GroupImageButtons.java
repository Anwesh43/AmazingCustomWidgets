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
        private float viewW,viewH;
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
            float y = 0,w = viewW/2,h=w*0.2f,x = w*0.2f;
            for(int i=0;i<imageButtons.size();i++) {
                x+=w*0.2f*2;
                if(x>viewW) {
                    h += w*0.2f;
                    x = w*0.2f;
                }
            }
            x = w*0.2f;
            y = viewH-h;
            for(GroupImageButton imageButton:imageButtons) {
                imageButton.setDimensions(x,y,w,w);
                x+=w*0.2f*2;
                if(x>viewW) {
                    y += w*0.2f;
                    x = 0;
                }
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX(),y = event.getY(),prevX=0,prevY=0;
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated ) {
                for(GroupImageButton groupImageButton:imageButtons) {
                    if(groupImageButton.handleTap(x,y) && currButton==null && (prevButton == null || prevButton!=groupImageButton)) {
                        isAnimated = true;
                        currButton = groupImageButton;
                        currButton.startAnimating(0,viewW/2,viewH/2,0.2f,72);
                        prevX = currButton.getX();
                        prevY = currButton.getY();
                    }
                    else if(currButton!=null) {
                        groupImageButton.startAnimating(1,prevX,prevY,0,0);
                        prevX = groupImageButton.getX();
                        prevY = groupImageButton.getY();
                    }
                }
                if(prevButton!=null) {
                    prevButton.startAnimating(1,prevX,prevY,-0.2f,-72);
                }
                if(currButton!=null) {
                    isAnimated = true;
                    postInvalidate();
                }
            }
            return true;
        }
    }
}
