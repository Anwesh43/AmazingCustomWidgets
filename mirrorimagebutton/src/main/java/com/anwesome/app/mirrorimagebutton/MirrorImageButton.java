package com.anwesome.app.mirrorimagebutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anweshmishra on 28/02/17.
 */
public class MirrorImageButton {
    private Activity activity;
    private MirrorImageButtonView mirrorImageButtonView;
    public MirrorImageButton(Activity activity) {
        this.activity = activity;
    }
    public void show(int x,int y) {
        if(mirrorImageButtonView == null) {
            mirrorImageButtonView = new MirrorImageButtonView(activity);
            activity.addContentView(mirrorImageButtonView,new ViewGroup.LayoutParams(400,400));
        }
        mirrorImageButtonView.setX(x);
        mirrorImageButtonView.setY(y);
    }
    private class MirrorImageButtonView extends View {
        public MirrorImageButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
    }
}
