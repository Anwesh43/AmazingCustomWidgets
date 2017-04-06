package com.anwesome.ui.likebutton;

import android.app.Activity;
import android.content.Context;
import android.view.View;

/**
 * Created by anweshmishra on 07/04/17.
 */
public class LikeButton  {
    private Activity activity;
    private LikeButtonView likeButtonView;
    public LikeButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {
        if(likeButtonView == null) {
            likeButtonView = new LikeButtonView(activity);
        }
    }
    private class LikeButtonView extends View {
        public LikeButtonView(Context context) {
            super(context);
        }
    }
}
