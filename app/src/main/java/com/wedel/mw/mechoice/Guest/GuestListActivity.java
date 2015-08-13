package com.wedel.mw.mechoice.Guest;

import android.support.v4.app.Fragment;

import com.wedel.mw.mechoice.SingleFragmentActivity;

/**
 * Created by mw on 02/08/15.
 */
public class GuestListActivity extends SingleFragmentActivity {

    //    public static final String EXTRA_GUESTLIST_COOKINGSESSION_UUID_STRING
//            = "com.wedel.mw.mechoice.cooking_session_is_true";
    public static final String EXTRA_GUESTLIST_ID = "com.wedel.mw.mechoic.guestlist_id";

    private static final String TAG = "GuestListActivity";


    protected Fragment createFragment() {


        long id = (long) getIntent().getSerializableExtra(EXTRA_GUESTLIST_ID);

        return GuestListFragment.newInstance(id);
    }


    public void setTitle(String title) {
        setTitle(title);

    }

}
