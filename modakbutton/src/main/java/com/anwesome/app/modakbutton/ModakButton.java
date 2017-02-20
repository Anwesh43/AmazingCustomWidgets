package com.anwesome.app.modakbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anweshmishra on 21/02/17.
 */
public class ModakButton {
    private Activity activity;
    private LineMover lineMover;
    private MotionStore motionStore;
    private ModakButtonView view;
    private View.OnClickListener onClickListener;
    public ModakButton(Activity activity) {
        this.activity = activity;
        motionStore = new MotionStore();
    }
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
    public void show(float x,float y) {
        if(view == null) {
            view = new ModakButtonView(activity);
            activity.addContentView(view,new ViewGroup.LayoutParams(300,300));
        }
        view.setX(x);
        view.setY(y);
    }
    private class ModakButtonView extends View {
        private int time = 0;
        private boolean isAnimated = false;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public ModakButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            int w = canvas.getWidth(),h = canvas.getHeight();
            if(time == 0) {
                lineMover = new LineMover(w/2,h/2,w/3);
            }
            paint.setColor(Color.GRAY);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(canvas.getWidth()/40);
            canvas.drawCircle(w/2,h/2,w/3,paint);
            lineMover.draw(canvas,paint);
            time++;
            if(isAnimated) {
                update();
                try {
                    Thread.sleep(50);
                    invalidate();
                } catch (Exception ex) {

                }
            }
        }
        private void update() {
            switch (motionStore.getMode()) {
                case 0:
                    lineMover.addDimensions();
                    if(lineMover.shouldStart()) {
                        motionStore.setMode(1);
                    }
                    break;
                case 1:
                    if(lineMover.shouldStop()) {
                        motionStore.setMode(0);
                        isAnimated = false;
                        if(onClickListener!=null) {
                            onClickListener.onClick(this);
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated) {
                isAnimated = true;
                postInvalidate();
            }
            return true;
        }
    }

}
