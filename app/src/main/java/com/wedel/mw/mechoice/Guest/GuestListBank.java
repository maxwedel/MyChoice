package com.wedel.mw.mechoice.Guest;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import com.wedel.mw.mechoice.Guest.GuestDatabaseHelper.GuestCursor;
import com.wedel.mw.mechoice.Guest.GuestDatabaseHelper.GuestListCursor;
/**
 * Created by mw on 04/08/15.
 */
public class GuestListBank {

    private static final String TAG = "GuestlistBank";

    private static GuestListBank sGuestListBank;
    private Context mAppContext;
    private ArrayList<GuestList> mGuestList;


    private GuestDatabaseHelper mGuestDBHelper;


    private GuestListBank(Context c) {
        mAppContext = c;
        mGuestDBHelper = GuestDatabaseHelper.get(mAppContext);

    }

    public static GuestListBank get(Context c) {

        if (sGuestListBank == null) {
            sGuestListBank = new GuestListBank(c.getApplicationContext());
        }
        return sGuestListBank;
    }


    public GuestList addGuestList() {

        //insert GuestList into the db
        GuestList gl = insertGuestList();
        return gl;

    }

    public GuestList getGuestList(long glID) {
        GuestList gl = null;
        GuestListCursor cursor = mGuestDBHelper.queryGuestList(glID);
        cursor.moveToFirst();
        // if a row is there
        if (!cursor.isAfterLast())
            gl = cursor.getGuestList();
        cursor.close();
        return gl;
    }


    /**
     *
     * @return a cursor with all guest in the database
     */
    public GuestCursor gueryGuest() {
        return mGuestDBHelper.queryGuests();
    }

    /**
     *
     * @param guestList
     * @return a Cursor with rows filled with guest associated to the given GuestList
     */
    public GuestCursor gueryGuest(long guestListID){
        return mGuestDBHelper.gueryGuestOnGuestlist(guestListID);
    }

    public Guest getGuest(long id) {
        Guest guest = null;
        GuestCursor cursor = mGuestDBHelper.queryGuest(id);
        cursor.moveToFirst();
        if (!cursor.isAfterLast())
            guest = cursor.getGuest();
        cursor.close();
        return guest;
    }

    /**
     * Method return true if the Guest is on the given Guestlist
     * @param GuestList
     * @param Guest
     * @return boolean true if guest on guestlist
     */
    public boolean isOnGuestList(long glID, Guest guest){

        GuestDatabaseHelper.GuestListSCursor cursor= mGuestDBHelper.getAllGuest(glID);
        cursor.moveToFirst();
        if (!cursor.isAfterLast())
            if(cursor.getGuestListS()==guest.getID())
                return true;
        return false;


    }

    public Guest insertGuest() {
        Guest guest = new Guest();
        guest.setID(mGuestDBHelper.insertGuest(guest));
        return guest;
    }
    public void updateGuest(Guest guest){

        mGuestDBHelper.update(guest);
    }

    private GuestList insertGuestList() {
        GuestList gl = new GuestList();
        gl.setId(mGuestDBHelper.insertGuestList(gl));
        return gl;
    }

    public void inserGuestToGuestList(long glID, Guest guest){

        mGuestDBHelper.interGuestTOGuestList(guest, glID);
        Log.e(TAG, " New Guest inserted in Guest List " + glID); // TODO: 10/08/15 REMOVE Error LOG

    }
    public void removeGuestToGuestList(long glID, Guest guest){

        mGuestDBHelper.removeGuestFROMGuestList(guest, glID);

    }

    public void getGuestInGuestList (GuestList gl){

        GuestDatabaseHelper.GuestListSCursor cursor=
                mGuestDBHelper.getAllGuest(gl.getId());

        cursor.moveToFirst();

        if(!cursor.isAfterLast())
            cursor.getGuestListS();



    }

}
