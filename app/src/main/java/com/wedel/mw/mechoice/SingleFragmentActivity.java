package com.wedel.mw.mechoice;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * Created by mw on 21/07/15.
 *
 * Abstract Class to host and manage the FragmentManager
 *
 */
public abstract class SingleFragmentActivity extends FragmentActivity {


    public static final String TAG = "SingleFragmentActivity";

    /*
    createFragment needs to implemented to create the Fragment the activity should be hosting
     */
    protected abstract Fragment createFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        


        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }

    }

}


