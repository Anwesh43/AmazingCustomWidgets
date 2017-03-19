package com.anwesome.games.deletebutton;

import android.app.Activity;

/**
 * Created by anweshmishra on 20/03/17.
 */
public class DeleteButton  {
    private Activity activity;
    private DeleteButton(Activity activity) {
        this.activity = activity;
    }
    public static DeleteButton getInstance(Activity activity) {
        return new DeleteButton(activity);
    }
    public void show() {
    }
}
