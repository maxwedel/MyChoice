package com.wedel.mw.mechoice.CookingSession;

import android.content.Intent;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.wedel.mw.mechoice.Guest.GuestList;
import com.wedel.mw.mechoice.Guest.GuestListActivity;
import com.wedel.mw.mechoice.Guest.GuestListBank;
import com.wedel.mw.mechoice.R;
import com.wedel.mw.mechoice.Recipe.AsyncParseJSONRecipe;
import com.wedel.mw.mechoice.Recipe.RecipeListActivity;
import com.wedel.mw.mechoice.Recipe.RecipeListFragment;

import java.util.UUID;

/**
 * Created by mw on 04/08/15.
 */
public class CookingSessionFragment extends Fragment {

    public static final String EXTRA_COOKINGSESSION_ID = "com.wedel.mw.mechoice";
    private static final String TAG = "CookingSessionFragment";


    private CookingSession mCookingSession;


    private EditText mCookingSessionName;

    private Button mRecipeList;
    private Button mNewGuestButton;
    private Button mViewGuestListButton;
    private long mCookingSessionId;
    CookingSessionBank mCooseBank;

    private long mGuestListID;

    private GuestListBank glBANK;
    private GuestList mGuestList;



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

        glBANK = GuestListBank.get(getActivity());
        mGuestList = glBANK.addGuestList();
        Log.e(TAG, " GuestlistId is: " + mGuestList.getId());
        mCookingSession = new CookingSession();
        mCookingSession.setmGuestListID(mGuestList.getId());
        mCookingSessionId = getArguments().getLong(EXTRA_COOKINGSESSION_ID);
        mCooseBank = CookingSessionBank.get(getActivity());

    }

    public static CookingSessionFragment newInstance(long mCookingSessionId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_COOKINGSESSION_ID, mCookingSessionId);

        CookingSessionFragment fragment = new CookingSessionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_cooking_session, container, false);

        mCookingSessionName = (EditText) v.findViewById(R.id.cooking_session_name_edittext);
//        mCookingSessionName.setText(mCookingSession.getName());
        mRecipeList = (Button) v.findViewById(R.id.cooking_session_recipe_list_button);
        mRecipeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String Uri = mCooseBank.checkRecipe(mCookingSessionId);

                Intent iRecipeList = new Intent(getActivity(), RecipeListActivity.class);
                iRecipeList.putExtra(RecipeListActivity.EXTRA_URI, Uri);
                startActivity(iRecipeList);

            }
        });

        mNewGuestButton = (Button) v.findViewById(R.id.cooking_session_new_guest_button);
        //TODO add listener to add a new Guest to the CookingSession Guestlist
        mViewGuestListButton = (Button) v.findViewById(R.id.cooking_session_view_guest_list_button);
        mViewGuestListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                long id = mGuestList.getId();
                Log.e(TAG,"GL id: "+ id);
                Intent iGuestList = new Intent(getActivity(), GuestListActivity.class);
                iGuestList.putExtra(GuestListActivity.EXTRA_GUESTLIST_ID, id);

                startActivity(iGuestList);
            }
        });

        return v;
    }

    //TODO: When creating a new CookingSession Guest LIst Intent.putExtraString(UUID.toString()


}



