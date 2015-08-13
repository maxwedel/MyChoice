package com.wedel.mw.mechoice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.wedel.mw.mechoice.CookingSession.CookingSession;
import com.wedel.mw.mechoice.CookingSession.CookingSessionActivity;
import com.wedel.mw.mechoice.CookingSession.CookingSessionBank;
import com.wedel.mw.mechoice.CookingSession.CookingSessionFragment;
import com.wedel.mw.mechoice.Guest.GuestDatabaseHelper;
import com.wedel.mw.mechoice.Guest.GuestList;
import com.wedel.mw.mechoice.Guest.GuestListActivity;
import com.wedel.mw.mechoice.Guest.GuestListBank;

/**
 * Created by mw on 03/08/15.
 */
public class SplashFragment extends Fragment {

    private static final String TAG = "com.wedel.mw.mechoice";
    private static final String ShrPref = "MySharedPreference";

    private TextView mWelcomeText;
    private Button mNewCookingSession;
    private Button mNewfavoriteGuestList;
    private GuestDatabaseHelper DBHelper;



    /**
     * Called to do initial creation of a fragment.  This is called after
     * {@link #onAttach(Activity)} and before
     * {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * <p>
     * <p>Note that this can be called while the fragment's activity is
     * still in the process of being created.  As such, you can not rely
     * on things like the activity's content view hierarchy being initialized
     * at this point.  If you want to do work once the activity itself is
     * created, see {@link #onActivityCreated(Bundle)}.
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DBHelper = GuestDatabaseHelper.get(getActivity());



        SharedPreferences ratePrefs = getActivity().getSharedPreferences("First update", 0);

        if (!ratePrefs.getBoolean("FIRST_RUN", false)) {
            SharedPreferences.Editor edit = ratePrefs.edit();
            edit.putBoolean("FIRST_RUN", true);
            edit.commit();
            JSONSerializer jsonSerializer = new JSONSerializer(getActivity());
            jsonSerializer.readIngrJSON();

            GuestList gl = new GuestList();
            DBHelper = GuestDatabaseHelper.get(getActivity());
            gl.setId(DBHelper.insertGuestList(gl));
        }

    }

    public static SplashFragment newInstance() {

        Bundle args = new Bundle();

        SplashFragment fragment = new SplashFragment();

        return fragment;
    }


    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null (which
     * is the default implementation).  This will be called between
     * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
     * <p>
     * <p>If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_splash, container, false);

        // initiate favorite guestlist
        //GuestList.get(getActivity());

        mWelcomeText = (TextView) v.findViewById(R.id.welcome_text);
        mNewCookingSession = (Button) v.findViewById(R.id.splash_button_new_cooking_session);
        mNewCookingSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CookingSession cookingSession = new CookingSession();
                CookingSessionBank.get(getActivity()).addCookingSession(cookingSession);
                Intent iCookingSession = new Intent(getActivity(), CookingSessionActivity.class);
                iCookingSession.putExtra(CookingSessionFragment
                        .EXTRA_COOKINGSESSION_ID, cookingSession.getmId());
                startActivity(iCookingSession);

            }
        });
        mNewfavoriteGuestList = (Button) v.findViewById(R.id.splash_button_new_favorite_guestlist);
        mNewfavoriteGuestList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuestListBank glb = GuestListBank.get(getActivity());
                Intent i = new Intent(getActivity(), GuestListActivity.class);
                long id  = 1;
                i.putExtra(GuestListActivity.EXTRA_GUESTLIST_ID, id);
                startActivity(i);

            }
        });


        return v;
    }
}
