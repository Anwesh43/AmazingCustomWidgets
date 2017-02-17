package com.anwesome.app.customactionsheet;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.anwesome.app.valueanimatoradapter.AnimatorAdapter;
import com.anwesome.ui.dimensionsutil.DimensionsUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 17/02/17.
 */
public class ActionSheet {
    private Activity activity;
    private boolean isAnimating = false;
    private String title;
    private OverlayView overlayView;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private List<ActionButton> actionButtons = new ArrayList<>();
    private ActionSheetView actionSheetView;
    private ValueAnimator inAnim,outAnim;
    public ActionSheet(Activity activity) {
        this.activity = activity;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void addAction(String action,ActionListener actionListener) {
        actionButtons.add(new ActionButton(action,actionListener));
    }

    public void show() {
        if(actionSheetView == null && overlayView == null) {
            overlayView = new OverlayView(activity);
            Point size = DimensionsUtil.getDeviceDimension(activity);
            int w = size.x,h = size.y;
            actionSheetView = new ActionSheetView(activity);
            int x = 0,y = ActionSheetConstants.TITLE_FONT+25;
            for(ActionButton actionButton:actionButtons) {
                actionButton.setDimensions(x,y,w,2*ActionSheetConstants.TEXT_FONT);
                y+=2*ActionSheetConstants.TEXT_FONT;
            }
            overlayView.setVisibility(View.INVISIBLE);
            overlayView.setX(0);
            overlayView.setY(0);
            activity.addContentView(overlayView,new ViewGroup.LayoutParams(w,h));
            actionSheetView.setX(0);
            actionSheetView.setY(h);
            activity.addContentView(actionSheetView,new ViewGroup.LayoutParams(w,y));
            inAnim = ValueAnimator.ofFloat(h,h-y);
            outAnim = ValueAnimator.ofFloat(h-y,h);
            inAnim.setDuration(500);
            outAnim.setDuration(500);
            AnimatorAdapter animatorAdapter = getActionSheetAnimAdapter();
            inAnim.addUpdateListener(animatorAdapter);
            inAnim.addListener(animatorAdapter);
            outAnim.addUpdateListener(animatorAdapter);
            outAnim.addListener(animatorAdapter);
        }
        overlayView.setVisibility(View.VISIBLE);
        inAnim.start();
    }
    private AnimatorAdapter getActionSheetAnimAdapter() {
        return new AnimatorAdapter(){
            public void onAnimationUpdate(ValueAnimator animator) {
                float y = (float)animator.getAnimatedValue();
                actionSheetView.setY(y-actionSheetView.getMeasuredHeight()/2);
            }
            public void onAnimationStart(Animator animator) {
                isAnimating = true;
            }
            public void onAnimationEnd(Animator animator) {
                isAnimating = false;
            }
        };
    }
    class ActionSheetView extends View {
        public ActionSheetView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            int w = canvas.getWidth(),h = canvas.getHeight();
            paint.setColor(Color.parseColor("#f9f9f9"));
            int r = Math.max(w,h)/10;
            canvas.drawRoundRect(new RectF(0,0,w,h),r/10,r/10,paint);
            paint.setColor(Color.parseColor("#d6d6da"));
            paint.setTextSize(25);
            canvas.drawText(title,w/2-paint.measureText(title)/2,20+paint.getTextSize()/2,paint);
            int y = ActionSheetConstants.TITLE_FONT+25;
            for(ActionButton button:actionButtons) {
                paint.setColor(Color.parseColor("#d6d6da"));
                canvas.drawLine(0,y,w,y,paint);
                button.draw(canvas,paint);
                y+=button.getH();
            }
        }
        public boolean onTouchEvent(MotionEvent event){
            float x = event.getX(),y = event.getY();
            ActionButton clickedButton = null;
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimating) {
                for(ActionButton actionButton:actionButtons) {
                    if(actionButton.handleTap(x,y)) {
                        isAnimating = true;
                        clickedButton = actionButton;
                        break;
                    }
                }
                if(isAnimating && clickedButton!=null) {
                    outAnim.start();
                    overlayView.setVisibility(INVISIBLE);
                    if(clickedButton.getActionListener()!=null) {
                        clickedButton.getActionListener().doAction();
                    }

                }
            }
            return true;
        }
    }
    public interface ActionListener {
        void doAction();
    }
    private class ActionButton {
        private String text;
        private float x,y,w,h;
        private ActionListener actionListener;
        public ActionButton(String text,ActionListener actionListener) {
            this.text = text;
            this.actionListener = actionListener;
        }
        public void setDimensions(float x,float y,float w,float h) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h  = h;
        }

        public ActionListener getActionListener() {
            return actionListener;
        }

        public void setActionListener(ActionListener actionListener) {
            this.actionListener = actionListener;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public float getX() {
            return x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }

        public float getW() {
            return w;
        }

        public void setW(float w) {
            this.w = w;
        }

        public float getH() {
            return h;
        }

        public void setH(float h) {
            this.h = h;
        }

        public void draw(Canvas canvas, Paint paint) {
            paint.setTextSize(ActionSheetConstants.TEXT_FONT);
            paint.setColor(Color.parseColor("#448AFF"));
            canvas.drawText(text,x+w/2-paint.measureText(text)/2,y+h/2+paint.getTextSize()/3,paint);
        }
        public int hashCode() {
            return text.hashCode()+(int)y;
        }
        public boolean handleTap(float x,float y) {
            return x>=this.x && x<=this.x+this.w && y>=this.y && y<=this.y+h;
        }
    }
    private class OverlayView extends View {
        public OverlayView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            canvas.drawColor(Color.parseColor("#AA000000"));
        }
    }
}
