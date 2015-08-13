package com.wedel.mw.mechoice.Guest;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.wedel.mw.mechoice.JSONSerializer;
import com.wedel.mw.mechoice.guest_taste.Ingr;

import java.sql.Date;

/**
 * Created by mw on 05/08/15.
 */
public class GuestDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "GuestDataBaseHelper";

    private static final String DB_NAME = "guest.sqlite";
    private static final int VERSION = 2;
    private boolean firstRun;
    private boolean favGL;

    private static GuestDatabaseHelper sDBHelper;

    public static GuestDatabaseHelper get(Context c) {
        if (sDBHelper == null) {
            sDBHelper = new GuestDatabaseHelper(c);
        }
        return sDBHelper;
    }

    // Table for Guests
    private static final String TABLE_GUEST = "guest";
    private static final String COLUMN_GUEST_ID = "_id";
    private static final String COLUMN_GUEST_NAME = "guest_name";
    private static final String COLUMN_GUEST_FAV_GUEST = "guest_fav";
    private static final String COLUMN_GUEST_CREATED_DATE = "guest_created_date";

    // Table for Guest_List
    private static final String TABLE_GUEST_LIST = "guest_list";
    private static final String COLUMN_GUEST_LIST_ID = "_id";
    private static final String COLUMN_GUEST_LIST_CREATED_DATE = "guest_list_created_date";

    // Table for all Guest in Guest_lists
    private static final String TABLE_GUEST_LISTS = "guest_list_s";
    private static final String COLUMN_GUEST_LISTS_GUEST = "guest_list_s_guests_id";
    private static final String COLUMN_GUEST_LISTS_ID = "guest_list_s_id";

    // All TABLES  for the Guest Taste DB

    // Table Ingr
    private static final String TABLE_INGR = "ingr";
    private static final String COLUMN_INGR_ID = "_id";
    private static final String COLUMN_INGR_DESCR = "ingr_desrciption";
    private static final String COLUMN_INGR_TERM = "ingr_term";
    private static final String COLUMN_INGR_SEARCHVALUE = "ingr_searchvalue";
    private static final String COLUMN_INGR_LIKE = "ingr_like";
    private static final String COLUMN_INGR_DISLIKE = "ingr_dislike";
    private static final String COLUMN_INGR_GUEST_ID = "ingr_guest_id";
    private static final String COLUMN_INGR_COOSE_ID = "ingr_coose_id";


    // TABLE ALRGY
    private static final String TABLE_ALRGY = "alrgy";
    private static final String COLUMN_ALRGY_ID = "alrgy_id";
    private static final String COLUMN_ALRGY_SHRT_DESCR = "alrgy_shortDescription";
    private static final String COLUMN_ALRGY_LONG_DESCR = "alrgy_longDescription";
    private static final String COLUMN_ALRGY_SEARCHVALUE = "alrgy_searchvalue";
    private static final String COLUMN_ALGRY_LIKE = "like";
    private static final String COLUMN_ALRGY_DISLIKE = "dislike";
    private static final String COLUMN_ALRGY_GUEST_ID = "guest_id";
    private static final String COLUMN_ALRGY_COOSE_ID = "coose_id";

    // TABLE DIET
    private static final String TABLE_DIET = "diet";
    private static final String COLUMN_DIET_ID = "diet_id";
    private static final String COLUMN_DIET_SHRT_DESCR = "diet_shortDescription";
    private static final String COLUMN_DIET_LONG_DESCR = "diet_longDescription";
    private static final String COLUMN_DIET_SEARCHVALUE = "diet_searchvalue";
    private static final String COLUMN_DIET_HAVE = "diet_have";
    private static final String COLUMN_DIET_GUEST_ID = "guest_id";
    private static final String COLUMN_DIET_COOSE_ID = "coose_id";

    // TABLE HLDAY
    private static final String TABLE_HLDAY = "hlday";
    private static final String COLUMN_HLDAY_ID = "_id";
    private static final String COLUMN_HLDAY_YUMMLY_ID = "hlday_yummli_id";
    private static final String COLUMN_HLDAY_NAME = "hlday_name";
    private static final String COLUMN_HLDAY_DESCR = "hlday_description";
    private static final String COLUMN_HLDAY_SEARCHVALUE = "hlday_searchvalue";
    private static final String COLUMN_HLDAY_HAVE = "hlday_have";
    private static final String COLUMN_HLDAY_GUEST_ID = "guest_id";
    private static final String COLUMN_HLDAY_COOSE_ID = "coose_id";

    // TABLE COURSE
    private static final String TABLE_COURSE = "course";
    private static final String COLUMN_COURSE_ID = "_id";
    private static final String COLUMN_COURSE_YUMMLY_ID = "course_yummli_id";
    private static final String COLUMN_COURSE_NAME = "course_name";
    private static final String COLUMN_COURSE_DESCR = "course_description";
    private static final String COLUMN_COURSE_SEARCHVALUE = "course_searchvalue";
    private static final String COLUMN_COURSE_HAVE = "course_have";
    private static final String COLUMN_COURSE_GUEST_ID = "guest_id";
    private static final String COLUMN_COURSE_COOSE_ID = "coose_id";

    // TABLE CUISINE
    private static final String TABLE_CUISINE = "cuisine";
    private static final String COLUMN_CUISINE_ID = "_id";
    private static final String COLUMN_CUISINE_YUMMLY_ID = "cuisine_yummli_id";
    private static final String COLUMN_CUISINE_NAME = "cuisine_name";
    private static final String COLUMN_CUISINE_DESCR = "cuisine_description";
    private static final String COLUMN_CUISINE_SEARCHVALUE = "cuisine_searchvalue";
    private static final String COLUMN_CUISINE_HAVE = "cuisine_have";
    private static final String COLUMN_CUISINE_GUEST_ID = "guest_id";
    private static final String COLUMN_CUISINE_COOSE_ID = "coose_id";

    private Context context;

    private GuestDatabaseHelper(Context c) {
        super(c, DB_NAME, null, VERSION);
        context = c;

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        // The Guest TABLE
        db.execSQL("create table guest (" +
                "_id integer primary key" +
                ", guest_name text" +
                ", guest_fav integer" +
                ", guest_created_date integer)");

        // GuestList TABLE
        db.execSQL("create table guest_list (" +
                "_id integer primary key" +
                ", guest_list_name text" +
                ", cooking_session_id integer" +
                ", guest_list_created_date integer)");

        // TABLE to store multiple Guest with the associated GuestList
        db.execSQL("create table guest_list_s (" +
                " guest_list_s_guests_id integer" +
                ", guest_list_s_id integer references guest_list(_id))");

        db.execSQL("create table ingr (" +
                "_id integer primary key" +
                ", ingr_desrciption text" +
                ", ingr_term text" +
                ", ingr_searchvalue text" +
                ", ingr_like integer" +
                ", ingr_dislike integer" +
                ", ingr_guest_id integer" +
                ", ingr_coose_id integer)");

//
//        String Ingr_DB_Creation = "create table ingr (" +
//                "_id integer primary key" +
//                ", ingr_desrciption text" +
//                ", ingr_term text" +
//                ", ingr_searchvalue text" +
//                ", like integer" +
//                ", dislike integer" +
//                ", guest_id integer" +
//                ", coose_id integer)";
//
//        String Alrgy_DB_Creation = "create table alrgy (" +
//                "alrgy_id integer" +
//                ", alrgy_shortDescription text" +
//                ", alrgy_longDescription text" +
//                ", alrgy_searchvalue text" +
//                ", like integer" +
//                ", dislike integer" +
//                ", guest_id integer" +
//                ", coose_id integer)";
//
//        String Diet_DB_Creation = "create table allergy (" +
//                "diet_id integer" +
//                ", diet_shortDescription text" +
//                ", diet_longDescription text" +
//                ", diet_searchvalue text" +
//                ", diet_have integer" +
//                ", guest_id integer" +
//                ", coose_id integer)";
//
//        String Hlday_DB_Creation = "create table hlday (" +
//                "_id integer primary key" +
//                ", hlday_yummli_id text" +
//                ", hlday_name text" +
//                ", hlday_description text" +
//                ", hlday_searchvalue text" +
//                ", hlday_have integer" +
//                ", guest_id integer" +
//                ", coose_id integer)";
//
//        String Course_DB_Creation = "create table course (" +
//                "_id integer primary key" +
//                ", course_yummli_id text" +
//                ", course_name text" +
//                ", course_description text" +
//                ", course_searchvalue text" +
//                ", course_have integer" +
//                ", guest_id integer" +
//                ", coose_id integer)";
//        String Cuisine_DB_Creation = "create table cuisine (" +
//                "_id integer primary key" +
//                ", cuisine_yummli_id text" +
//                ", cuisine_name text" +
//                ", cuisine_description text" +
//                ", cuisine_searchvalue text" +
//                ", cuisine_have integer" +
//                ", guest_id integer" +
//                ", coose_id integer)";

        // Creating all TABLE that describe the taste of a guest
        //db.execSQL(Ingr_DB_Creation);


//        db.execSQL(Alrgy_DB_Creation);
//        db.execSQL(Diet_DB_Creation);
//        db.execSQL(Cuisine_DB_Creation);
//        db.execSQL(Hlday_DB_Creation);
//        db.execSQL(Course_DB_Creation);

//
//        if (!firstRun)
//            firstRun = initateCoreData(context);
    }

    private void checkFavGL() {

        initateFavGL(context);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GUEST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GUEST_LIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GUEST_LISTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGR);
        onCreate(db);
        Log.e(TAG, "DB upgraded");


        GuestList gl = new GuestList();
        gl.setId(insertGuestList(gl));


        SharedPreferences ratePrefs = context.getSharedPreferences("First update", 0);
        SharedPreferences.Editor edit = ratePrefs.edit();
        edit.putBoolean("FIRST_RUN", false);
        edit.commit();

    }


    /**
     * If the Core data (Ingredients, cuisine, diet, course etc has not yet been inserted to the DB
     * then this method will initiate the insertion from json files from Assets Files
     *
     * @param context
     * @return boolean
     */
    public boolean initateCoreData(Context context) {


        SharedPreferences ratePrefs = context.getSharedPreferences("First update", 0);


        if (!ratePrefs.getBoolean("FIRST_RUN", false)) {

            JSONSerializer jsonSerializer = new JSONSerializer(context);
            jsonSerializer.loadInBackground();
            SharedPreferences.Editor edit = ratePrefs.edit();
            edit.putBoolean("FIRST_RUN", true);
            edit.commit();


            return true;
        }
        return false;
    }

    public boolean initateFavGL(Context context) {


        SharedPreferences ratePrefs = context.getSharedPreferences("Fav GuestList", 0);


        if (!ratePrefs.getBoolean("FavGL", false)) {
            ContentValues cv = new ContentValues();
            GuestList gl = new GuestList();
            cv.put(COLUMN_GUEST_LIST_CREATED_DATE, gl.getCreatedDate().getTime());
            getWritableDatabase().insert(TABLE_GUEST_LIST, null, cv);
            SharedPreferences.Editor edit = ratePrefs.edit();
            edit.putBoolean("FavGL", true);
            edit.commit();


            return true;
        }
        return false;
    }


    /**
     * Performs INNER JOIN with TABLE guest and guest_list
     *
     * @param gl Guestlist
     * @return GuestCursor wrapper of all guest that are on the given Guestlist
     */
    public GuestCursor gueryGuestOnGuestlist(long glID) {
        String MY_QUERY = "SELECT " +
                " guest._id" +
                ",guest.guest_name" +
                ",guest.guest_fav" +
                ",guest.guest_created_date" +
                " FROM guest INNER JOIN guest_list_s " +
                "ON guest._id=guest_list_s.guest_list_s_guests_id " +
                "WHERE guest_list_s.guest_list_s_id = ?";
        Cursor wrapper = getReadableDatabase()
                .rawQuery(MY_QUERY, new String[]{String.valueOf(glID)}); // TODO: 07/08/15 REMOVE  !!!!!! // put gl.getId()
        return new GuestCursor(wrapper);
    }


    // Guest related methods and inner Cursor Class
    //=============================================================================================

    /**
     * Inserts a new Guest into the DB.
     *
     * @param guest
     * @return long ID of the newly created row
     */
    public long insertGuest(Guest guest) {
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_GUEST_NAME, guest.getName().toString());
        cv.put(COLUMN_GUEST_FAV_GUEST, guest.getIntBoolean());
        cv.put(COLUMN_GUEST_CREATED_DATE, guest.getCreateDate().getTime());
        return getWritableDatabase().insert(TABLE_GUEST, null, cv);
    }


    public void update(Guest guest) {
        ContentValues cv = new ContentValues();
        long id = guest.getID();

        cv.put(COLUMN_GUEST_NAME, guest.getName().toString());
        cv.put(COLUMN_GUEST_FAV_GUEST, guest.getIntBoolean());
        cv.put(COLUMN_GUEST_CREATED_DATE, guest.getCreateDate().getTime());
        getWritableDatabase().update("guest", cv, COLUMN_GUEST_ID + " = ?",
                new String[]{String.valueOf(id)});

        Log.d(TAG, "Database was updated");
    }

    public GuestCursor queryGuests() {
        Cursor wrapper = getReadableDatabase().rawQuery("select * from guest", null);
        return new GuestCursor(wrapper);
    }


    public GuestCursor queryGuest(long id) {
        Cursor wrapper = getReadableDatabase()
                .query(TABLE_GUEST,
                        null,
                        COLUMN_GUEST_ID + " = ?",
                        new String[]{String.valueOf(id)},
                        null, // group by
                        null, // order by
                        null, // having
                        "1"); // limit 1 row
        return new GuestCursor(wrapper);
    }

    public static class GuestCursor extends CursorWrapper {

        public GuestCursor(Cursor c) {
            super(c);
        }

        public Guest getGuest() {
            if (isBeforeFirst() || isAfterLast()) return null;

            String name = getString(getColumnIndex(COLUMN_GUEST_NAME));
            int fav = getInt(getColumnIndex(COLUMN_GUEST_FAV_GUEST));
            long id = getLong(getColumnIndex(COLUMN_GUEST_ID));
            Date date = new Date(getLong(getColumnIndex(COLUMN_GUEST_CREATED_DATE)));


            return new Guest(id, name, fav, date);
        }

        public Guest getGuest(GuestList gl) {
            if (isBeforeFirst() || isAfterLast()) return null;

            String name = getString(getColumnIndex(COLUMN_GUEST_NAME));
            int fav = getInt(getColumnIndex(COLUMN_GUEST_FAV_GUEST));
            long id = getLong(getColumnIndex(COLUMN_GUEST_ID));
            Date date = new Date(getLong(getColumnIndex(COLUMN_GUEST_CREATED_DATE)));


            return new Guest(id, name, fav, date);
        }
    }


    // Guest_list related methods and inner Cursor Class
    //=============================================================================================

    public long insertGuestList(GuestList guestList) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_GUEST_LIST_CREATED_DATE, guestList.getCreatedDate().getTime());
        return getWritableDatabase().insert(TABLE_GUEST_LIST, null, cv);

    }


    public GuestListCursor queryGuestList() {
        Cursor wrapper = getReadableDatabase().rawQuery("select * from guest_list", null);
        return new GuestListCursor(wrapper);
    }

    public GuestListCursor queryGuestList(long id) {
        if (id == 1) {
            checkFavGL();
        }
        Cursor wrapper = getReadableDatabase()
                .query(TABLE_GUEST_LIST,
                        null,
                        COLUMN_GUEST_LIST_ID + "=?",
                        new String[]{String.valueOf(id)},
                        null,
                        null,
                        null,
                        "1");


        return new GuestListCursor(wrapper);

    }

    public static class GuestListCursor extends CursorWrapper {

        public GuestListCursor(Cursor c) {
            super(c);
        }

        public GuestList getGuestList() {
            if (isBeforeFirst() || isAfterLast()) return null;

            long id = getLong(getColumnIndex(COLUMN_GUEST_LIST_ID));
            Date date = new Date(getLong(getColumnIndex(COLUMN_GUEST_LIST_CREATED_DATE)));

            return new GuestList(id, date);
        }
    }


    // CursorWrapper and methods to store guest in the Guest_list table
    //=============================================================================================

    /**
     * inner Cursor Wrapper for the Guest in Guestlists
     */
    public static class GuestListSCursor extends CursorWrapper {

        public GuestListSCursor(Cursor c) {
            super(c);
        }

        public long getGuestListS() {
            if (isBeforeFirst() || isAfterLast()) return -1;

            return getLong(getColumnIndex(COLUMN_GUEST_LISTS_GUEST));

        }
    }

    /**
     * Inserts a new Guest into an existing Guestlist in the Table GUEST_LIST_S
     *
     * @param guest
     * @param guestListID
     */
    public long interGuestTOGuestList(Guest guest, long guestListID) {
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_GUEST_LISTS_GUEST, guest.getID());
        cv.put(COLUMN_GUEST_LISTS_ID, guestListID);
        long result = getWritableDatabase().insert(TABLE_GUEST_LISTS, null, cv);
        if (result == -1) Log.e(TAG, " error inserting new Guest to Guest Lists S");

        Log.e(TAG, "Guest " + guest.getName() + " was added to Guest List " + guestListID);
        return result;
    }

    public long removeGuestFROMGuestList(Guest guest, long guestListID) {

        long GUESTdelete_id = guest.getID();
        long GuestList_id = guestListID;


        String[] args = new String[]{String.valueOf(GUESTdelete_id), String.valueOf(GuestList_id)};

        long result = getWritableDatabase().delete(TABLE_GUEST_LISTS
                , "guest_list_s_guests_id" + " =? " + " AND " + "guest_list_s_id" + " =? "
                , args);


        if (result == -1) Log.e(TAG, " error removing  Guest from Guest Lists S");
        else Log.i(TAG, "Guest: "
                + guest.getID()
                + " was removed from Guest List: "
                + guestListID);

        return result;


    }

    /**
     * @param guestListId
     * @return a Cursor  with all GuestID associated with the given guestListId
     */
    public GuestListSCursor getAllGuest(long guestListId) {
        Cursor wrapper = getReadableDatabase().query(TABLE_GUEST_LISTS,
                null,
                COLUMN_GUEST_LISTS_ID + "= ?",
                new String[]{String.valueOf(guestListId)},
                null,
                null,
                null,
                null);
        return new GuestListSCursor(wrapper);


    }

    // class CursorWrapper Ingr & insertIngr() & getIngr() & deleteIngr() & queryIngr()
    //=============================================================================================


    public IngrCursor queryAllIngr() {
        Cursor wrapper = getReadableDatabase().rawQuery("select DISTINCT ingr._id" +
                ", ingr.ingr_desrciption" +
                ",ingr.ingr_term" +
                ",ingr.ingr_searchvalue" +
                ",ingr.ingr_like" +
                ",ingr.ingr_dislike" +
                ",ingr.ingr_guest_id" +
                ",ingr.ingr_coose_id" +
                " from ingr", null);
        return new IngrCursor(wrapper);
    }

    public IngrCursor queryIngr(long id) {
        Cursor wrapper = getReadableDatabase()
                .query(TABLE_INGR,
                        null,
                        COLUMN_INGR_ID + " = ?",
                        new String[]{String.valueOf(id)},
                        null, // group by
                        null, // order by
                        null, // having
                        "1"); // limit 1 row
        return new IngrCursor(wrapper);

    }

    /**
     * if guestORcoose is true the ingr associated to the guest_id will be deleted
     * if guestORcoose is false the ingr associated to the coose_id will be deleted
     *
     * @param delete_id
     * @param guestORcoose
     * @return number of rows effected by the deleteprocess
     */
    public int deletIngr(long delete_id, boolean guestORcoose) {


        String where;
        if (guestORcoose) {
            where = "ingr_guest_id = ?";
        } else {
            where = "ingr_coose_id = ?";
        }

        String[] args = new String[]{String.valueOf(delete_id)};
        Log.e(TAG, " deleted coose||guest id: " +delete_id );
        return getWritableDatabase().delete(TABLE_INGR, where, args);

    }

    public static class IngrCursor extends CursorWrapper {

        public IngrCursor(Cursor c) {
            super(c);
        }

        public Ingr getIngr() {
            if (isBeforeFirst() || isAfterLast()) return null;


            long id = getLong(getColumnIndex(COLUMN_INGR_ID));

            String descr = getString(getColumnIndex(COLUMN_INGR_DESCR));
            String term = getString(getColumnIndex(COLUMN_INGR_TERM));
            String searchvalue = getString(getColumnIndex(COLUMN_INGR_SEARCHVALUE));

            int like = getInt(getColumnIndex(COLUMN_INGR_LIKE));


            int dislike = getInt(getColumnIndex(COLUMN_INGR_DISLIKE));

            long guest_id = getLong(getColumnIndex(COLUMN_INGR_GUEST_ID));
            long coose_id = getLong(getColumnIndex(COLUMN_INGR_COOSE_ID));


            return new Ingr(id, descr, term, searchvalue, like, dislike, guest_id, coose_id);
        }

    }

    public long insertIngr(Ingr ingr) {
        ContentValues cv = new ContentValues();


        cv.put(COLUMN_INGR_DESCR, String.valueOf(ingr.getmDesrc()));
        cv.put(COLUMN_INGR_TERM, String.valueOf(ingr.getmTerm()));
        cv.put(COLUMN_INGR_SEARCHVALUE, String.valueOf(ingr.getmSearchvalue()));

        cv.put(COLUMN_INGR_LIKE, ingr.getmLike());

        cv.put(COLUMN_INGR_DISLIKE, ingr.getmDislike());
        cv.put(COLUMN_INGR_GUEST_ID, ingr.getmGuest_id());
        cv.put(COLUMN_INGR_COOSE_ID, ingr.getmCoose_id());

        Log.e(TAG, "ingr added: "+ ingr.getmDesrc() +
                " guest: "+ ingr.getmGuest_id()+ " coose: " +ingr.getmCoose_id());


        return getWritableDatabase().insert(TABLE_INGR, null, cv);
    }

    public IngrURICursor queryLIKEIngr( long coose_id) {

        String[] columns = new String[]{String.valueOf(COLUMN_INGR_SEARCHVALUE
                + ", " + COLUMN_INGR_LIKE
                + ", " + COLUMN_INGR_COOSE_ID)};
        //String[] where = new String[]{String.valueOf("ingr.ingr_coose_id =? AND ingr.ingr_like =1")};
        String where = "ingr.ingr_coose_id =? AND ingr.ingr_like =1";
        String[] args = new String[]{String.valueOf(coose_id)};

        Cursor wrapper = getWritableDatabase().query(
                true // distince
                , TABLE_INGR  // table
                , columns // which columns should be shown
                , where //
                , args // where arguments
                , null
                , null
                , null
                , null);

        return new IngrURICursor(wrapper);
    }

        public static class IngrURICursor extends CursorWrapper {

            public IngrURICursor(Cursor c) {
                super(c);
            }

            public String getINGR() {
                if (isBeforeFirst() || isAfterLast()) return null;
                Log.e(TAG, "read query");

                String searchvalue = getString(getColumnIndex(COLUMN_INGR_SEARCHVALUE));
                return searchvalue;
            }
        }

    }


