package com.anwesome.ui.messengerbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

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
    public void show() {
        if(messengerButtonView == null) {
            messengerButtonView = new MessengerButtonView(activity);
            
        }
    }
    private class MessengerButtonView extends View {
        public MessengerButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
