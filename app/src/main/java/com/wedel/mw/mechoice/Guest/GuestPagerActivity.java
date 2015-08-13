package com.wedel.mw.mechoice.Guest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.wedel.mw.mechoice.R;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by mw on 02/08/15.
 */
public class GuestPagerActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private ArrayList<Guest> mGuests;
    public static final String TAG = "GuestPagerActivity";


    /**
     * Perform initialization of all fragments and loaders.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);
        mGuests = GuestList.get(this).getGuest();

        //getActionBar().setDisplayHomeAsUpEnabled(true); // no backbutton because real button is better in this case

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Guest guest = mGuests.get(position);
                return null;

            }

            @Override
            public int getCount() {
                return mGuests.size();
            }
        });

    mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }

        @Override
        public void onPageSelected(int position) {
            // super.onPageSelected(position);

            Guest guest = mGuests.get(position);
            if (guest.getName() != null) {
                String title = guest.getName();
                setTitle(title); //// FIXME: 03/08/15 set title is not done when ViewPager is first initiated
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            super.onPageScrollStateChanged(state);
        }
    });

//        UUID guestID = (UUID)getIntent().getSerializableExtra(GuestFragment.EXTRA_GUEST_ID);
//        for(int i=0; i<mGuests.size();i++) {
//            if(mGuests.get(i).getId().equals(guestID)){
//                mViewPager.setCurrentItem(i);
//
//                break;
//            }
//        }

    }

    /**
     * Initialize the contents of the Activity's standard options menu.  You
     * should place your menu items in to <var>menu</var>.
     * <p/>
     * <p>This is only called once, the first time the options menu is
     * displayed.  To update the menu every time it is displayed, see
     * {@link #onPrepareOptionsMenu}.
     * <p/>
     * <p>The default implementation populates the menu with standard system
     * menu items.  These are placed in the {@link Menu#CATEGORY_SYSTEM} group so that
     * they will be correctly ordered with application-defined menu items.
     * Deriving classes should always call through to the base implementation.
     * <p/>
     * <p>You can safely hold on to <var>menu</var> (and any items created
     * from it), making modifications to it as desired, until the next
     * time onCreateOptionsMenu() is called.
     * <p/>
     * <p>When you add items to the menu, you can implement the Activity's
     * {@link #onOptionsItemSelected} method to handle them there.
     *
     * @param menu The options menu in which you place your items.
     * @return You must return true for the menu to be displayed;
     * if you return false it will not be shown.
     * @see #onPrepareOptionsMenu
     * @see #onOptionsItemSelected
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //TODO: add menu inflator for cooking session

        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.fragment_guest_list_favorite_guest,menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.action_add){
            Log.d(TAG, "Add User clicked");
        }

        return super.onOptionsItemSelected(item);
    }
}
