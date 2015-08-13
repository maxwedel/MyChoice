package com.wedel.mw.mechoice.Guest;

import android.support.v4.app.Fragment;

import com.wedel.mw.mechoice.SingleFragmentActivity;

/**
 * Created by mw on 02/08/15.
 */
public class GuestActivity extends SingleFragmentActivity {

    protected Fragment createFragment() {
        return GuestFragment
                .newInstance(
                        (long)getIntent().getSerializableExtra(GuestFragment.EXTRA_GUEST_ID)
                        ,(long)getIntent().getSerializableExtra(GuestFragment.EXTRA_GUEST_LIST_ID));
    }


}
