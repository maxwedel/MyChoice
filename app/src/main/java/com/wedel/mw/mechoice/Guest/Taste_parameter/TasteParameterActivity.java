package com.wedel.mw.mechoice.Guest.Taste_parameter;

import android.support.v4.app.Fragment;

import com.wedel.mw.mechoice.Guest.GuestDatabaseHelper;
import com.wedel.mw.mechoice.SingleFragmentActivity;

/**
 * Created by mw on 09/08/15.
 */
public class TasteParameterActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {

        return IngrListFragment.newInstance((long)getIntent()
                .getSerializableExtra(IngrListFragment.EXTRA_GUEST_ID));
    }
}
