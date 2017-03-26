package com.anwesome.demos.playbackbutton;

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
 * Created by anweshmishra on 27/03/17.
 */
public class PlaybackButton {
    private PlaybackStateController playbackStateController = new PlaybackStateController();
    private Activity activity;
    private PlaybackButtonView playbackButtonView;
    private PlaybackButtonType type;
    public PlaybackButton(Activity activity,PlaybackButtonType type) {
        this.activity = activity;
        this.type = type;
    }
    public void show(int x,int y) {
        if(playbackButtonView==null) {
            playbackButtonView = new PlaybackButtonView(activity);
            Point size = DimensionsUtil.getDeviceDimension(activity);
            playbackStateController.init(playbackButtonView,size.x/3,size.x/3,type);
            activity.addContentView(playbackButtonView,new ViewGroup.LayoutParams(size.x/3,size.x/3));
        }
        playbackButtonView.setX(x);
        playbackButtonView.setY(y);
    }
    private class PlaybackButtonView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public PlaybackButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(playbackStateController!=null) {
                playbackStateController.render(canvas,paint);
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(playbackStateController!=null) {
                playbackStateController.handleTap(event);
            }
            return true;
        }
    }
}
