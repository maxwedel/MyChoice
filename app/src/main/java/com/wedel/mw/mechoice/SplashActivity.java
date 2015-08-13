package com.wedel.mw.mechoice;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by mw on 02/08/15.
 */
public class SplashActivity extends SingleFragmentActivity {

    protected Fragment createFragment() {
        return SplashFragment.newInstance();
    }



}
