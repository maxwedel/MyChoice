package com.wedel.mw.mechoice;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.AsyncTaskLoader;

/**
 * Created by mw on 07/08/15.
 */
public abstract class SQLiteCursorLoader extends AsyncTaskLoader<Cursor> {

    private Cursor mCursor;

    public SQLiteCursorLoader(Context context) {
        super(context);
    }

    protected abstract Cursor loadCursor();


    @Override
    public Cursor loadInBackground() {


        Cursor cursor = loadCursor();
        if(cursor !=null){
            //
            cursor.getCount();
        }
        return cursor;
    }

    @Override
    public void deliverResult(Cursor data) {
        Cursor oldCursor = mCursor; // stuff old cursor in variable old cursor and store new data in mCursor
        mCursor = data;

        if(isStarted()){
            super.deliverResult(data);
        }
        if(oldCursor != null && oldCursor != data && !oldCursor.isClosed()){
            oldCursor.close();
        }
    }


    @Override
    protected void onStartLoading() {

        if(mCursor !=null){
            deliverResult(mCursor);
        }
        if (takeContentChanged() || mCursor == null){
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        // cancel the current load task if possible
        cancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();

        onStopLoading();
        if(mCursor != null && !mCursor.isClosed()){
            mCursor.close();
        }
        mCursor=null;
    }
}
