package com.wedel.mw.mechoice.Guest;

import com.wedel.mw.mechoice.PreferenceList;

import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;

/**
 * Created by mw on 02/08/15.
 */
public class Guest {

    private UUID mId;
    private long id;
    private String name;
    private PreferenceList mPreferenceList;
    private boolean isFavoriteGuest;
    private Date createDate;

//    public UUID getId() {
//        return mId;
//    }


    public long getID(){
        return id;
    }
    public void setID(long newID){
        this.id = newID;
    }

    public String getName() {
        return name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public PreferenceList getmPreferenceList() {
        return mPreferenceList;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setIsFavoriteGuest(boolean isFavoriteGuest) {
        this.isFavoriteGuest = isFavoriteGuest;
    }

    public boolean getIsFavoriteGuest() {
        return isFavoriteGuest;
    }

    public Guest() {

        createDate = new Date();
        name = "Name ... ";
        isFavoriteGuest = false;

    }

    public Guest( long id, String name, int fav, Date createDate) {
        this.createDate = createDate;
        this.id = id;
        this.name = name;
        if (fav ==0) isFavoriteGuest=false;
        else isFavoriteGuest=true;
        //TODO: Preference list is not being delt with

    }

    /**
     * Returns a string containing a concise, human-readable description of this
     * object. Subclasses are encouraged to override this method and provide an
     * implementation that takes into account the object's type and data. The
     * default implementation is equivalent to the following expression:
     * <pre>
     *   getClass().getName() + '@' + Integer.toHexString(hashCode())</pre>
     * <p>See <a href="{@docRoot}reference/java/lang/Object.html#writing_toString">Writing a useful
     * {@code toString} method</a>
     * if you intend implementing your own {@code toString} method.
     *
     * @return a printable representation of this object.
     */
    @Override
    public String toString() {
        return name.toString();
    }

    public int getIntBoolean() {
        if (isFavoriteGuest == true) {
            return 1;
        } else return 0;
    }

    public String prefAsString() {

        return "No Value"; // FIXME: UUID for the user Preference has to be stored> at the moment "No value"
    }

}
