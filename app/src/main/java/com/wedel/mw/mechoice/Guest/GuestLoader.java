package com.wedel.mw.mechoice.Guest;

import android.content.Context;

/**
 * Created by mw on 10/08/15.
 */
public class GuestLoader extends DataLoader<Guest> {

    private long mGuestId;

    public GuestLoader(Context context, long mGuestId) {
        super(context);
        this.mGuestId = mGuestId;
    }

    @Override
    public Guest loadInBackground() {
        return GuestListBank.get(getContext()).getGuest(mGuestId);
    }
}
