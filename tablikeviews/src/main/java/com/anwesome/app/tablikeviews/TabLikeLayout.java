package com.anwesome.app.tablikeviews;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 15/02/17.
 */
public class TabLikeLayout {
    private Activity activity;
    private TabLikeView tabLikeView;
    private ConcurrentLinkedQueue<TabElement> tabs = new ConcurrentLinkedQueue<>();
    private int w,h,tabW,tabH;
    private float x = 0;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public TabLikeLayout(Activity activity) {
        this.activity = activity;
        initWH();
    }
    public void initWH() {
        Point size = new Point();
        w = size.x;
        h = size.y;
        paint.setTextSize(h/60);
        tabW = w;
        tabH = h/40;
    }
    public void addTab(TabElement tabElement) {
        float text_W =  paint.measureText(tabElement.getTitle());
        float currTabW = 2*text_W,currTabh = 2*paint.getTextSize();
        if(x+currTabW>tabW) {
            tabH+=currTabh;
            x = 0;
        }
        tabElement.setDimensions(x,tabH,currTabW,currTabh);
        x+=currTabW;
        tabs.add(tabElement);
    }
    public void show() {
        if(tabLikeView == null) {
            tabLikeView = new TabLikeView(activity);
            activity.addContentView(tabLikeView,new ViewGroup.LayoutParams(tabW,tabH+3*(int)paint.getTextSize()));
        }
    }
    private class TabLikeView extends View {
        public TabLikeView(Context context){
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
