package com.anwesome.games.deletebutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 20/03/17.
 */
public class DeleteButton  {
    private Activity activity;
    private DeleteButtonController deleteButtonController;
    private DeleteButtonView deleteButtonView;
    private DeleteButton(Activity activity) {
        this.activity = activity;
    }
    public static DeleteButton getInstance(Activity activity) {
        return new DeleteButton(activity);
    }
    public void show() {
        if(deleteButtonView == null) {
            deleteButtonView = new DeleteButtonView(activity);
        }
    }
    private class DeleteButtonView extends View {
        private boolean isAnimated = false;
        public DeleteButtonView(Context context){
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(isAnimated) {
                try {
                    Thread.sleep(50);
                    invalidate();
                }
                catch (Exception ex) {

                }
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated) {

            }
            return true;
        }
    }
}
