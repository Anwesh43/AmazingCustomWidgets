package com.anwesome.demos.notepadbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 28/03/17.
 */
public class NotepadButton {
    private Activity activity;
    private NotepadButton notepadButton;
    private NotepadButtonView notepadButtonView;
    private NotepadButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {

    }
    private class NotepadButtonView extends View {
        private boolean isAnimated = false;
        public NotepadButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            try {
                Thread.sleep(50);
                invalidate();
            }
            catch (Exception ex) {

            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated) {
                postInvalidate();
                isAnimated = true;
            }
            return  true;
        }
    }
}
