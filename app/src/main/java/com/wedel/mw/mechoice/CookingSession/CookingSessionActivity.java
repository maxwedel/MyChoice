package com.wedel.mw.mechoice.CookingSession;

import android.support.v4.app.Fragment;

import com.wedel.mw.mechoice.SingleFragmentActivity;

import java.util.UUID;

/**
 * Created by mw on 04/08/15.
 */
public class CookingSessionActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return  CookingSessionFragment.newInstance((long)getIntent()
                .getSerializableExtra(CookingSessionFragment.EXTRA_COOKINGSESSION_ID));
    }
}
