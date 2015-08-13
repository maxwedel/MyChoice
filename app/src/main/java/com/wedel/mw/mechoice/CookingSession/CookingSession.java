package com.wedel.mw.mechoice.CookingSession;

        import android.content.Context;
        import android.util.Log;

        import com.wedel.mw.mechoice.Guest.Guest;
        import com.wedel.mw.mechoice.Guest.GuestList;
        import com.wedel.mw.mechoice.Guest.GuestListBank;

        import java.util.Date;
        import java.util.UUID;

/**
 * Created by mw on 02/08/15.
 */
public class CookingSession {

    public static final String TAG = "CookingSession";

    private long mId;
    private String name;
    private Date mDate;
    private long mGuestListID;

    // private ArrayList<RecipeListItem> mRecipes; // TODO: create RecipeListItem Class

    //TODO: Add RecipeListItem list to Cooking Session

    public CookingSession() {
        mId = 1;

        Log.d(TAG, "Cooking Session created"); //TODO remove TAG
        mDate = new Date();
    }



    public long getGuestListID() {
        return mGuestListID;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public void setmGuestListID(long mGuestListID) {
        this.mGuestListID = mGuestListID;
    }


    public long getmId() {
        return mId;
    }

    public Date getmDate() {
        return mDate;
    }

    public long getmGuestListID() {
        return mGuestListID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

