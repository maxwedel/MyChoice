package com.wedel.mw.mechoice.CookingSession;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wedel.mw.mechoice.R;

import java.util.ArrayList;

/**
 * Created by mw on 05/08/15.
 */
public class CookingSessionListFragment extends ListFragment {

    private ArrayList<CookingSession> mCookingSessions;
    private static final String TAG = "CookingSessionListFragment ";

    //FIXME TODO add button to splash to open and see all cookingsessionslist

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().setTitle("Cooking Sessions");

        mCookingSessions = CookingSessionBank.get(getActivity()).getCookingSessions();

        CookingSessionAdapter mCustomAdpater = new CookingSessionAdapter(mCookingSessions);
        setListAdapter(mCustomAdpater);


    }

    private class CookingSessionAdapter extends ArrayAdapter<CookingSession>{

        public CookingSessionAdapter(ArrayList<CookingSession> cookingSessions){
            super(getActivity(),0,cookingSessions);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView == null){
                convertView = getActivity()
                        .getLayoutInflater()
                        .inflate(R.layout.list_item_cookingsession, null);
            }
            CookingSession cs = getItem(position);

            TextView titleTextView = (TextView)convertView
                    .findViewById(R.id.cookingsession_list_nameText);
            titleTextView.setText(cs.getName());

            return convertView;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        ((CookingSessionAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        //FIXME: CookingSessionList needs a costum menu
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
        //FIXME: CookingSession Menu Bar
    }
}
