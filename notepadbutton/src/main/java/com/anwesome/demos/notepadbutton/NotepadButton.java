package com.anwesome.demos.notepadbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

/**
 * Created by anweshmishra on 28/03/17.
 */
public class NotepadButton {
    private Activity activity;
    private String text;
    private Notepad notepad;
    private NotepadButtonView notepadButtonView;
    public NotepadButton(Activity activity,String text) {
        this.activity = activity;
        this.text = text;
    }
    public void show() throws Exception {
        if(notepadButtonView!=null) {
            notepadButtonView = new NotepadButtonView(activity);
            Point size = DimensionsUtil.getDeviceDimension(activity);
            int w = size.x;
            notepad = Notepad.getInstance(w/4,w/4,w/4,text);
            activity.addContentView(notepadButtonView,new ViewGroup.LayoutParams(w/2,w/2));
        }
    }
    private class NotepadButtonView extends View {
        private boolean isAnimated = false;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public NotepadButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            notepad.draw(canvas,paint);
            try {
                notepad.update();
                if(notepad.stopped()) {
                    isAnimated = false;
                }
                Thread.sleep(50);
                invalidate();
            }
            catch (Exception ex) {

            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated) {
                notepad.startMoving();
                postInvalidate();
                isAnimated = true;
            }
            return  true;
        }
    }
}
