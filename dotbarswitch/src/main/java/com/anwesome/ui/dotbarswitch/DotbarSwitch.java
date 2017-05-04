package com.anwesome.ui.dotbarswitch;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 04/05/17.
 */
public class DotbarSwitch {
    private Activity activity;
    private DotbarSwitchView dotbarSwitchView;
    private int render = 0,yGap,x;
    private List<DotbarSwitchShape> dotbarSwtichShapes = new ArrayList<>();
    private AnimationHandler animationHandler;
    public DotbarSwitch(Activity activity) {
        this.activity = activity;
    }
    public void show() {
        if(dotbarSwitchView == null) {
            dotbarSwitchView = new DotbarSwitchView(activity);
            animationHandler = new AnimationHandler(dotbarSwitchView,dotbarSwtichShapes);
            Point size = DimensionsUtil.getDeviceDimension(activity);
            int w = size.x;
            yGap = w/10;
            x = w/4;
            activity.addContentView(dotbarSwitchView,new ViewGroup.LayoutParams(w,(3*dotbarSwtichShapes.size()/2)*(yGap*5)/4));
        }
    }
    public void addDotBar() {
        if(dotbarSwitchView == null) {
            DotbarSwitchShape dotbarSwitchShape = new DotbarSwitchShape();
            dotbarSwtichShapes.add(dotbarSwitchShape);
        }
    }
    private class DotbarSwitchView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public DotbarSwitchView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(render == 0) {
                float y = yGap*dotbarSwtichShapes.size()/2;
                for(DotbarSwitchShape dotbarSwitchShape:dotbarSwtichShapes) {
                    dotbarSwitchShape.setY(y);
                    y+=(yGap*5)/4;
                }
            }
            canvas.save();
            canvas.translate(x,0);
            for(DotbarSwitchShape dotbarSwitchShape:dotbarSwtichShapes) {
                dotbarSwitchShape.draw(canvas,paint,canvas.getWidth()/2);
            }
            canvas.restore();
            render++;
            animationHandler.animate();
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(animationHandler != null) {
                animationHandler.handleTouch(event,x);
            }
            return true;
        }
    }
}
