package com.anwesome.ui.messengerbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;
import com.anwesome.ui.messengerbutton.controller.AnimationController;


/**
 * Created by anweshmishra on 13/04/17.
 */
public class MessengerButton {
    private Activity activity;
    private MessengerButtonShape messengerButtonShape;
    private MessengerButtonView messengerButtonView;
    private AnimationController animationController;
    public MessengerButton(Activity activity) {
        this.activity = activity;
    }
    public void show(int x,int y) {
        if(messengerButtonView == null) {
            messengerButtonView = new MessengerButtonView(activity);
            Point dimension = DimensionsUtil.getDeviceDimension(activity);
            int w = dimension.x;
            activity.addContentView(messengerButtonView,new ViewGroup.LayoutParams(w/2,w/2));
            messengerButtonShape = new MessengerButtonShape(w/4,w/4,w/4);
            animationController = new AnimationController(messengerButtonView, new AnimationController.AnimationControllerListener() {
                @Override
                public void animate() {
                    messengerButtonShape.move();
                }

                @Override
                public boolean stop() {
                    return messengerButtonShape.stop();
                }

                @Override
                public void handleTap() {
                    messengerButtonShape.startMoving();
                }
            });
        }
        messengerButtonView.setX(x);
        messengerButtonView.setY(y);
    }
    private class MessengerButtonView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public MessengerButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            messengerButtonShape.draw(canvas,paint);
            animationController.update();
        }
        public boolean onTouchEvent(MotionEvent event) {
            animationController.handleTap(event.getAction());
            return true;
        }
    }
}
