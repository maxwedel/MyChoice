package com.wedel.mw.mechoice.Guest;

import android.app.Activity;
//import android.app.LoaderManager.LoaderCallbacks;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;


import com.wedel.mw.mechoice.Guest.Taste_parameter.IngrListFragment;
import com.wedel.mw.mechoice.Guest.Taste_parameter.TasteParameterActivity;
import com.wedel.mw.mechoice.Guest.Taste_parameter.TasteParameterManager;
import com.wedel.mw.mechoice.R;

/**
 * Created by mw on 02/08/15.
 */
public class GuestFragment extends Fragment {



    private static final String TAG = "GuestListFragment";
    public static final String EXTRA_GUEST_ID = "com.wedel.mw.mechoice.guest_id";
    public static final String EXTRA_GUEST_LIST_ID = "com.wedel.mw.mechoice.guest_list_id";
    private static final int LOADER_GUEST_ID = 0;

    private static final String ARG_SECTION_NUMBER = "section_number";
    private Guest mGuest; /* widgets from the fragment_guest.xml TODO: make EditText in guest_layout.xml with separator*/
    private GuestList mGuestList;

    //FIXME: Guest Fragment needs a custom menu (ActionBar/Toolbar)

    long guestlistID; // TODO: 07/08/15 REMOVE!!!!!

    /**
     * Called to do initial creation of a fragment.  This is called after {@link #onAttach(Activity)} and before {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}. <p/> <p>Note that this can be called while the fragment's activity is still in the process of being created.  As such, you can not rely on things like the activity's content view hierarchy being initialized at this point.  If you want to do work once the activity itself is created, see {@link #onActivityCreated(Bundle)}. @param savedInstanceState If the fragment is being re-created from a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        long mGuestId = args.getLong(EXTRA_GUEST_ID);
        guestlistID = getArguments().getLong(EXTRA_GUEST_LIST_ID);
        mGuestList = GuestListBank.get(getActivity()).getGuestList(guestlistID);
        mGuest = GuestListBank.get(getActivity()).getGuest(mGuestId);
        if (args != null) {

                LoaderManager lm = getLoaderManager();
                lm.initLoader(LOADER_GUEST_ID, args , new GuestLoaderCallbacks());

        }

    }

    public static GuestFragment newInstance(long guestId, long glID) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_GUEST_ID, guestId);
        args.putSerializable(EXTRA_GUEST_LIST_ID, glID); // TODO: 07/08/15 REMOVE !!
        GuestFragment fragment = new GuestFragment();
        fragment.setArguments(args);

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
        //return super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_guest, container, false);
        EditText mNameField = (EditText) v.findViewById(R.id.guest_name);
        mNameField.setText(mGuest.getName());
        mNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mGuest.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                GuestListBank.get(getActivity()).updateGuest(mGuest);
            }
        });

            final CheckBox mFavoriteGuest = (CheckBox) v.findViewById(R.id.guest_checkBox_favoriteGuest);
            if(mGuestList.getId()==1)
                mFavoriteGuest.setChecked(true); 
            mFavoriteGuest.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {

                        GuestListBank.get(getActivity()).inserGuestToGuestList(1, mGuest);
                        // TODO: 11/08/15 update the DB change the FAV status to 1
                    }
                    if (!isChecked) {
                        GuestListBank.get(getActivity()).removeGuestToGuestList(1, mGuest);
                    }

                }
            });


        Button mIngredientsLike = (Button) v.findViewById(R.id.guest_button_ingredients_like);
        mIngredientsLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent IngrIntent = new Intent(getActivity(), TasteParameterActivity.class);
                IngrIntent.putExtra(IngrListFragment.EXTRA_GUEST_ID, mGuest.getID());
                startActivity(IngrIntent);

            }
        });

        Button mIngredientsDislike = (Button) v.findViewById(R.id.guest_button_ingredients_dislike);

        Button mCuisine = (Button) v.findViewById(R.id.guest_button_cuisine);

        Button mAllergies = (Button) v.findViewById(R.id.guest_button_allergies);
        return v;

    }

    /**
     * Called when a fragment is first attached to its activity.
     * {@link #onCreate(Bundle)} will be called after this.
     *
     * @param activity
     */
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        ((GuestActivity)activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
//    }


    private class GuestLoaderCallbacks implements LoaderManager.LoaderCallbacks<Guest> {

        @Override
        public android.support.v4.content.Loader<Guest> onCreateLoader(int id, Bundle args) {
            return new GuestLoader(getActivity(), args.getLong(EXTRA_GUEST_ID));
        }

        @Override
        public void onLoadFinished(android.support.v4.content.Loader<Guest> loader, Guest data) {
            mGuest = data;
        }

        @Override
        public void onLoaderReset(android.support.v4.content.Loader<Guest> loader) {

        }

    }

}
