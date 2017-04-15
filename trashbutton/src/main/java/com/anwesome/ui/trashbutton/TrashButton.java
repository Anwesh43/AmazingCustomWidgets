package com.anwesome.ui.trashbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;
import com.anwesome.ui.trashbutton.controller.AnimationController;

/**
 * Created by anweshmishra on 16/04/17.
 */
public class TrashButton {
    private Activity activity;
    private TrashButtonShape trashButtonShape;
    private TrashButtonView trashButtonView;
    private TrashButtonShape.TrashButtonClickListener trashButtonClickListener;
    private AnimationController animationController;
    public TrashButton(Activity activity) {
        this.activity = activity;
    }
    public void setTrashButtonOnClickListener(TrashButtonShape.TrashButtonClickListener trashButtonOnClickListener) {
        this.trashButtonClickListener = trashButtonOnClickListener;
        if(trashButtonShape!=null) {
            trashButtonShape.setTrashButtonOnClickListener(trashButtonOnClickListener);
        }
    }
    public void show(int x,int y) {
        if(trashButtonView == null) {
            trashButtonView = new TrashButtonView(activity);
            trashButtonShape = new TrashButtonShape();
            animationController = new AnimationController(trashButtonView,trashButtonShape);
            Point size = DimensionsUtil.getDeviceDimension(activity);
            int w = size.x;
            activity.addContentView(trashButtonView,new ViewGroup.LayoutParams(w/2,w/2));
            if(trashButtonClickListener!=null) {
                trashButtonShape.setTrashButtonOnClickListener(trashButtonClickListener);
            }
        }
        trashButtonView.setX(x);
        trashButtonView.setY(y);
    }
    private class TrashButtonView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public TrashButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            trashButtonShape.draw(canvas,paint,canvas.getWidth()/2);
            animationController.animate();
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                animationController.startAnimating();
            }
            return true;
        }
    }
}
