package com.wedel.mw.mechoice.Guest.Taste_parameter;

import android.content.ContentValues;
import android.content.Context;

import com.wedel.mw.mechoice.Guest.GuestDatabaseHelper;
import com.wedel.mw.mechoice.Guest.GuestList;
import com.wedel.mw.mechoice.guest_taste.Ingr;


/**
 * Created by mw on 09/08/15.
 */
public class TasteParameterManager {

    private static TasteParameterManager  sTasteParameterManager;
    private Context mAppContext;
    private GuestDatabaseHelper mDBHeler;


    private TasteParameterManager(Context c){
        mAppContext = c;
        mDBHeler = GuestDatabaseHelper.get(mAppContext);
    }
    public static TasteParameterManager get(Context c){
        if (sTasteParameterManager == null){
            sTasteParameterManager = new TasteParameterManager(c.getApplicationContext());
        }
        return sTasteParameterManager;
    }

    public long insertIngr(Ingr ingr){
        return mDBHeler.insertIngr(ingr);
    }

    public GuestDatabaseHelper.IngrCursor queryIngr(){
        return mDBHeler.queryAllIngr();
        // TODO: 11/08/15 build query that return all ingredients inlcuding all with the
        // guestlist id, this
    }


    public Ingr getIngr(long id){
        Ingr ingr = null;
        GuestDatabaseHelper.IngrCursor cursor = mDBHeler.queryIngr(id);

        cursor.moveToFirst();
                if(!cursor.isAfterLast())
                    ingr = cursor.getIngr();
        cursor.close();
        return ingr;
    }
    public long inserIngr(Ingr ingr){
        return mDBHeler.insertIngr(ingr);
    }
    public int deleteIngr(long ingrID, boolean guestORCoose){
        return mDBHeler.deletIngr(ingrID, guestORCoose);
    }
}
