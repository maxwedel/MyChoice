package com.wedel.mw.mechoice.guest_taste;

/**
 * Created by mw on 09/08/15.
 */
public class Ingr {


        private long mID = 0;
        private String mDesrc ="";
        private String mTerm ="";
        private String mSearchvalue ="";
        private int mDislike =0;
        private int mLike =0;
        private long mGuest_id =0;
        private long mCoose_id =1;


    public Ingr(long id,
                String descr,
                String term,
                String searchvalue,
                int like,
                int dislike,
                long guest_id,
                long coose_id) {

        mID=id;
        mDesrc=descr;
        mTerm=term;
        mSearchvalue=searchvalue;
        mDislike=dislike;
        mGuest_id=guest_id;
        mCoose_id=coose_id;


    }
    public Ingr(
                String descr,
                String term,
                String searchvalue,
                int like,
                int dislike,
                long guest_id,
                long coose_id) {


        mDesrc=descr;
        mTerm=term;
        mSearchvalue=searchvalue;
        mDislike=dislike;
        mGuest_id=guest_id;
        mCoose_id=coose_id;


    }

    public Ingr(String search_value, String description, String term) {


        mTerm=term;
        mDesrc=description;
        mSearchvalue=search_value;


        mLike = -1;
        mDislike=-1;
        mGuest_id=-1;
        mCoose_id=1;

    }

    public Ingr() {
    }


    public long getmID() {
        return mID;
    }

    public void setmID(long mID) {
        this.mID = mID;
    }

    public String getmDesrc() {
        return mDesrc;
    }

    public void setmDesrc(String mDesrc) {
        this.mDesrc = mDesrc;
    }

    public String getmTerm() {
        return mTerm;
    }

    public void setmTerm(String mTerm) {
        this.mTerm = mTerm;
    }

    public String getmSearchvalue() {
        return mSearchvalue;
    }

    public void setmSearchvalue(String mSearchvalue) {
        this.mSearchvalue = mSearchvalue;
    }

    public int getmDislike() {

        return mDislike;

    }

    public void setmDislike(int mDislike) {
        this.mDislike = mDislike;
    }

    public int getmLike() {
        return mLike;
    }

    public void setmLike(int mLike) {
        this.mLike = mLike;
    }

    public long getmGuest_id() {
        return mGuest_id;
    }

    public void setmGuest_id(long mGuest_id) {
        this.mGuest_id = mGuest_id;
    }

    public long getmCoose_id() {
        return mCoose_id;
    }

    public void setmCoose_id(long mCoose_id) {
        this.mCoose_id = mCoose_id;
    }
}
