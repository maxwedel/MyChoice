package com.wedel.mw.mechoice.Guest.Taste_parameter;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;


/**
 * Created by mw on 09/08/15.
 */
public abstract class TastParameterList extends android.support.v4.app.ListFragment implements LoaderCallbacks<Cursor> {


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        getLoaderManager().initLoader(0, null, this); // TODO: 09/08/15 Loaders for each parameter fragment need id
    }
}
