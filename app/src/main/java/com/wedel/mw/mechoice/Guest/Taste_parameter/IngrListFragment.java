package com.wedel.mw.mechoice.Guest.Taste_parameter;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.wedel.mw.mechoice.Guest.Guest;
import com.wedel.mw.mechoice.Guest.GuestDatabaseHelper;
import com.wedel.mw.mechoice.Guest.GuestList;
import com.wedel.mw.mechoice.Guest.GuestListBank;
import com.wedel.mw.mechoice.R;
import com.wedel.mw.mechoice.SQLiteCursorLoader;
import com.wedel.mw.mechoice.guest_taste.Ingr;

/**
 * Created by mw on 09/08/15.
 */
public class IngrListFragment extends android.support.v4.app.ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

// TODO: 09/08/15 Need own guest_id

    public static final String EXTRA_GUEST_ID = "com.wedel.mw.mechoice.IngrFragment";
    private long mGuestID;
    private TasteParameterManager manager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        manager = TasteParameterManager.get(getActivity());
        mGuestID = getArguments().getLong(EXTRA_GUEST_ID);
        getLoaderManager().initLoader(0, null, this); // TODO: 09/08/15 Loaders for each parameter fragment need id
    }

    public static IngrListFragment newInstance(long gID){
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_GUEST_ID, gID);

        IngrListFragment fragment = new IngrListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    // Overriden methods from the interface LoaderCallbacks to handle the Loader
    @Override
    public android.support.v4.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new IngrListCursorLoader(getActivity()); // TODO: 07/08/15 See for loading more than only Guest
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<Cursor> loader, Cursor data) {
        IngrCursorAdapter adapter =
                new IngrCursorAdapter(getActivity(), (GuestDatabaseHelper.IngrCursor) data);
        setListAdapter(adapter);
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<Cursor> loader) {
        setListAdapter(null);
    }


    // Cursor Adapter
    private class IngrCursorAdapter extends CursorAdapter {

        private final GuestDatabaseHelper.IngrCursor ingrCursor;

        public IngrCursorAdapter(Context context, GuestDatabaseHelper.IngrCursor cursor) {

            super(context, cursor, 0);
            ingrCursor = cursor;
        }


        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            return inflater
                    .inflate(R.layout.list_item_ingr, parent, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {

            final Ingr ingr = ingrCursor.getIngr();

            TextView nameTextView = (TextView) view
                    .findViewById(R.id.ingr_list_nameTextView);
            nameTextView.setText(ingr.getmDesrc());

            final CheckBox likeCheckBox = (CheckBox) view
                    .findViewById(R.id.ingr_list_item_like_checkbox);

            if (ingr.getmLike() == 1) {
                likeCheckBox.setChecked(true);
            }
            if (ingr.getmDislike() == 1) {
                likeCheckBox.setChecked(false);
            }

            final CheckBox disLikeCheckBox = (CheckBox) view
                    .findViewById(R.id.ingr_list_item_dislike_checkbox);

        likeCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){

                    disLikeCheckBox.setClickable(false);
                    ingr.setmGuest_id(mGuestID);
                    ingr.setmLike(1);
                    ingr.setmCoose_id(1); // TODO: 12/08/15 REMOVE !!! swap with logic to associate with correct coose
                    manager.insertIngr(ingr);


                    // TODO: 09/08/15
                }
                if (!isChecked){
                    disLikeCheckBox.setClickable(true);
                    manager.deleteIngr(ingr.getmID(), true);
                    }

            }
        });
        disLikeCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){

                    likeCheckBox.setClickable(false);
                    // insert into db   // TODO: 09/08/15
                }
                if (!isChecked){
                    likeCheckBox.setClickable(true);
                    // delete ingredient   // TODO: 09/08/15
                }
            }
        });

            if(ingr.getmLike()!=1)
                likeCheckBox.setChecked(false);
                if(ingr.getmDislike()!=1)
                disLikeCheckBox.setChecked(false);

        }
    }


    // GuestListCursor loader
    private static class IngrListCursorLoader extends SQLiteCursorLoader {

        public IngrListCursorLoader(Context context) {
            super(context);

        }

        @Override
        protected Cursor loadCursor() {

            return TasteParameterManager.get(getContext()).queryIngr();
        }
    }

}
