package com.wedel.mw.mechoice.Guest;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by mw on 02/08/15.
 */
public class GuestList {

    private static final String TAG = "Guestlist";


    private static GuestList sGuestList;
    private Context mAppContext;
    private ArrayList<Guest> mGuest;
    private UUID mId;
    private long id;
    private String name;
    private GuestDatabaseHelper mHelper;
    private Date createdDate;




    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    private GuestList (Context mAppContext){
        this.mAppContext = mAppContext;
        mGuest = new ArrayList<Guest>();
        createdDate = new Date();
        fakeGuest(); // TODO: 06/08/15 remove fake guest creation method
    }

    public GuestList (long id, Date createdDate){
        this.id = id;
        this.createdDate = createdDate;
    }

    public GuestList(){
        createdDate = new Date();
    }

    public static GuestList get(Context c) {

        return new GuestList(c.getApplicationContext());

    }


    private void fakeGuest(){


        Log.e(TAG, "Fake GuestList created with three Guest");
        mGuest = new ArrayList<Guest>();
        Guest guest1 = new Guest();
        guest1.setName("Meister Hobel");
        guest1.setIsFavoriteGuest(true);
        mGuest.add(guest1);

        Guest guest2 = new Guest();
        guest2.setName("Hulk Hoagen");
        guest2.setIsFavoriteGuest(false);
        mGuest.add(guest2);

        Guest guest3 = new Guest();
        guest3.setName("Snow White");
        guest3.setIsFavoriteGuest(true);
        mGuest.add(guest3);

    }

//    public Guest getGuest(UUID id){
//        for(Guest g: mGuest) {
//            if (g.getId().equals(id))
//                return g;
//        }
//        return null;
//    }

    public ArrayList<Guest> getGuest(){

        return mGuest;
    }

    /**
     * This method will return a normal GuestList for the use in a CookingSession only
     * @return
     */

    public static GuestList guestListFactory(Context c){
        GuestList guestList = new GuestList();
        guestList.generateID(c);
        return guestList;
    }

    public void addGuest(Guest guest){

        mGuest.add(guest);
    }
    private void generateID(Context context){

        if(mId == null){
        mId = UUID.randomUUID();
        //mAppContext = context.getApplicationContext();
        }
    }


    // TODO: 06/08/15 Delete UUID methods
    public UUID getmId() {
        return mId;
    }



}
