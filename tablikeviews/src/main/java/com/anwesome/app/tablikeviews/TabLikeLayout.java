package com.anwesome.app.tablikeviews;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.anwesome.ui.dimensionsutil.DimensionsUtil;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 15/02/17.
 */
public class TabLikeLayout {
    private AppCompatActivity activity;
    private TabLikeView tabLikeView;
    private ConcurrentLinkedQueue<TabElement> tabs = new ConcurrentLinkedQueue<>();
    private int w,h,tabW,tabH,viewW,viewH;
    private float x = 0;
    private boolean tabChanging = false;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public TabLikeLayout(AppCompatActivity activity) {
        this.activity = activity;
        initWH();
    }
    public void initWH() {
        Point size = DimensionsUtil.getDeviceDimension(activity);
        w = size.x;
        h = size.y;
        paint.setTextSize(h/40);
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
        viewW =  tabW;
        viewH = h-tabH;
    }
    public void show() {
        if(tabLikeView == null) {
            FragmentTransactionManager.addViews(activity,tabs,w,h-tabH,tabH);
            tabLikeView = new TabLikeView(activity);
            tabH = tabH+3*(int)paint.getTextSize();
            activity.addContentView(tabLikeView,new ViewGroup.LayoutParams(tabW,tabH));
            tabLikeView.setX(0);
            tabLikeView.setY(0);
        }
    }
    private class TabLikeView extends View {
        private TabElement currTab,prevTab;
        private boolean isAnimated = false;
        private int time = 0;
        public TabLikeView(Context context){
            super(context);
        }
        public void initPrevTab() {
            for(TabElement tab:tabs) {
                prevTab = tab;
                break;
            }
            if(prevTab!=null) {
                prevTab.setDefault();
                FragmentTransactionManager.setDefaultView(prevTab);
            }
            FragmentTransactionManager.setOnTrsactionEndListener(new FragmentTransactionManager.TransactionEndHandler() {
                @Override
                public void handle() {
                    tabChanging = false;
                }
            });
        }
        public void onDraw(Canvas canvas) {
            if(time == 0) {
                initPrevTab();
            }
            canvas.drawColor(Color.parseColor("#00BCD4"));
            for(TabElement tab:tabs) {
                tab.draw(canvas,paint);
            }
            time++;
            if(isAnimated) {
                if(currTab!=null) {
                    currTab.update();
                }
                if(prevTab!=null) {
                    prevTab.update();
                }
                if((currTab!=null && currTab.isAnimStopped()) && (prevTab == null || (prevTab!=null && prevTab.isAnimStopped()))) {
                    isAnimated = false;
                    prevTab = currTab;
                    currTab = null;
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
            float x = event.getX(),y = event.getY();
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated && !tabChanging) {
                for(TabElement tabElement:tabs) {
                    if(tabElement.handleTap(x,y) && tabElement!=prevTab) {
                        currTab = tabElement;
                        break;
                    }
                }
                if(currTab!=null) {
                    currTab.setDir(1);
                    currTab.startAnimation();
                    if(prevTab!=null) {
                        prevTab.setDir(-1);
                        prevTab.startAnimation();
                        FragmentTransactionManager.switchView(activity,currTab.getFragment(),prevTab.getFragment());
                    }
                    isAnimated = true;
                    tabChanging = true;
                    postInvalidate();
                }
            }
            return true;
        }
    }
}
