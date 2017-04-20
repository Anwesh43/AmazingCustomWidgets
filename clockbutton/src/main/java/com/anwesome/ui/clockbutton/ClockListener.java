package com.anwesome.ui.clockbutton;

/**
 * Created by anweshmishra on 20/04/17.
 */
public interface ClockListener {
    void onProgress(int h,int m);
    void onCompletion(int h,int m);
}
