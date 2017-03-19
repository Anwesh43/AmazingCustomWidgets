package com.anwesome.games.deletebutton;

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
    public void show(int y) {
        if(deleteButtonView == null) {
            deleteButtonView = new DeleteButtonView(activity);
            Point size = DimensionsUtil.getDeviceDimension(activity);
            deleteButtonController = new DeleteButtonController(size.x/2,size.x/8,size.x/4,size.x);
            activity.addContentView(deleteButtonView,new ViewGroup.LayoutParams(size.x,size.x/4));
        }
        deleteButtonView.setY(y);
    }
    private class DeleteButtonView extends View {
        private boolean isAnimated = false;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public DeleteButtonView(Context context){
            super(context);
        }
        public void onDraw(Canvas canvas) {
            deleteButtonController.draw(canvas,paint);
            if(isAnimated) {
                deleteButtonController.update();
                if(deleteButtonController.isStopped()) {
                    isAnimated = false;
                }
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
                isAnimated = true;
                postInvalidate();
            }
            return true;
        }
    }
}
