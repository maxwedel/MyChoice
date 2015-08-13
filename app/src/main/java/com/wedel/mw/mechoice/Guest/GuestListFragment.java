package com.wedel.mw.mechoice.Guest;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.LoaderManager.LoaderCallbacks;


import com.wedel.mw.mechoice.Guest.GuestDatabaseHelper.GuestCursor;
import com.wedel.mw.mechoice.R;
import com.wedel.mw.mechoice.SQLiteCursorLoader;

/**
 * Created by mw on 02/08/15.
 */
public class GuestListFragment extends android.support.v4.app.ListFragment implements LoaderCallbacks<Cursor> {


    private static GuestList smGuestList;
    private Context mAppContext;
    public static final String EXTRA_GUESTLIST_ID = "com.wedel.mechoice.guestlist_id";
    public long glID;


    private static final String TAG = "GuestListFragment";
    public static final int REQUEST_CODE_GUESTFRAGMENT_CHANGE = 0;


    public GuestListFragment() {
    }


    public static GuestListFragment newInstance(long id) {

        Bundle args = new Bundle();

        args.putSerializable(EXTRA_GUESTLIST_ID, id);

        GuestListFragment fragment = new GuestListFragment();
        fragment.setArguments(args);

        if(smGuestList == null){
            smGuestList = new GuestList();
            smGuestList.setId(id);
        }

        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        glID = getArguments().getLong(EXTRA_GUESTLIST_ID);
        smGuestList = GuestListBank.get(getActivity()).getGuestList(glID);
        setHasOptionsMenu(true);
        getLoaderManager().initLoader(0,getArguments() , this);


        try {
            GuestListBank.get(getActivity()).getGuestList(1);
        } catch (NullPointerException e) {
            smGuestList = GuestListBank.get(getActivity()).addGuestList();
            Log.e(TAG, "Created Favorite Guestlist, id: " );
        }

    }

    /**
     * This method will be called when an item in the list is selected.
     * Subclasses should override. Subclasses can call
     * getListView().getItemAtPosition(position) if they need to access the
     * data associated with the selected item.
     *
     * @param l        The ListView where the click happened
     * @param v        The view that was clicked within the ListView
     * @param position The position of the view in the list
     * @param id       The row id of the item that was clicked
     */
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {


        Intent i = new Intent(getActivity(), GuestActivity.class);
        i.putExtra(GuestFragment.EXTRA_GUEST_ID, id);
        i.putExtra(GuestFragment.EXTRA_GUEST_LIST_ID, smGuestList.getId());
        startActivityForResult(i, REQUEST_CODE_GUESTFRAGMENT_CHANGE);
        Log.e(TAG, " clicked on Item to show in GuestFragment");
        Log.e(TAG, " Guest belongs to guestlist: " + smGuestList.getId());

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Found no other way to update the listview after changes to guest have been then
        // to restart the Loader completely
        if (REQUEST_CODE_GUESTFRAGMENT_CHANGE == requestCode)

            getLoaderManager().restartLoader(0, getArguments(), this);


    }

    // Overriden methods from the interface LoaderCallbacks to handle the Loader
    @Override
    public android.support.v4.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {

        return new GuestListCursorLoader(getActivity(),(long)args.getSerializable(EXTRA_GUESTLIST_ID)); // TODO: 07/08/15 See for loading more than only Guest
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<Cursor> loader, Cursor data) {
        GuestCursorAdapter adapter =
                new GuestCursorAdapter(getActivity(), (GuestCursor) data);
        setListAdapter(adapter);
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<Cursor> loader) {
        setListAdapter(null);
    }


    // Cursor Adapter
    private class GuestCursorAdapter extends CursorAdapter {

        private final GuestCursor mGuestCursor;

        public GuestCursorAdapter(Context context, GuestCursor cursor) {

            super(context, cursor, 0);
            mGuestCursor = cursor;
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            return inflater
                    .inflate(R.layout.list_item_guest, parent, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            // Get the guest from the current row
            Guest guest = mGuestCursor.getGuest();

            // if the guest is on the guestlist it will be shown
            // set up the name TextView
            TextView nameTextView = (TextView) view
                    .findViewById(R.id.guest_list_nameTextView);
            nameTextView.setText(guest.getName());


        }
    }


//    @Override
//    public void onResume() {
//        super.onResume();
//        ((GuestCursorAdapter) getListAdapter()).notifyDataSetChanged();
//        Log.e(TAG, "onResume called");
//
//    }


    // Menu options
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_guest_list_favorite_guest, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_add) {

            Guest NEWguest = GuestListBank.get(getActivity()).insertGuest();
            GuestListBank.get(getActivity()).inserGuestToGuestList(smGuestList.getId(), NEWguest);

            Intent i = new Intent(getActivity(), GuestActivity.class);
            i.putExtra(GuestFragment.EXTRA_GUEST_ID, NEWguest.getID());
            i.putExtra(GuestFragment.EXTRA_GUEST_LIST_ID, smGuestList.getId());
            startActivityForResult(i, REQUEST_CODE_GUESTFRAGMENT_CHANGE);

            Log.d(TAG, "Added new guest " + NEWguest.getID() + " to guestlist " + smGuestList.getId());


        }

        return super.onOptionsItemSelected(item);
    }


    // GuestListCursor loader
    private static class GuestListCursorLoader extends SQLiteCursorLoader {
            private long glID;
        public GuestListCursorLoader(Context context, long glID) {
            super(context);
            this.glID = glID;
        }

        @Override
        protected Cursor loadCursor() {

            return GuestListBank.get(getContext()).gueryGuest(glID);
        }
    }
}