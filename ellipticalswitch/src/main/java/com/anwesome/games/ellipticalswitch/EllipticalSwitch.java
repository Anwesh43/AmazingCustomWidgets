package com.anwesome.games.ellipticalswitch;

import android.app.Activity;

import com.anwesome.games.basicswitch.BasicSwitch;
import com.anwesome.games.basicswitch.OnSelectedListener;
import com.anwesome.games.basicswitch.SwitchObject;

/**
 * Created by anweshmishra on 06/03/17.
 */
public class EllipticalSwitch extends BasicSwitch {
    public EllipticalSwitch(Activity activity) {
        super(activity);
    }
    public SwitchObject getSwitchObject(OnSelectedListener onSelectedListener) {
        return new EllipticalSwitchObject(onSelectedListener);
    }
}
