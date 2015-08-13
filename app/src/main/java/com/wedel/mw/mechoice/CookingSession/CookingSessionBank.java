package com.wedel.mw.mechoice.CookingSession;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.wedel.mw.mechoice.Guest.GuestDatabaseHelper;

import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by mw on 04/08/15.
 */
public class CookingSessionBank {

    private static final String TAG = "CookingSessionBank";

    private static CookingSessionBank sCookingSessionBank;
    private Context mAppContext;
    private ArrayList<CookingSession> mCookingSessions;
    private GuestDatabaseHelper DBHelper;

    private CookingSessionBank(Context c) {
        mAppContext = c;
        mCookingSessions = new ArrayList<CookingSession>();
        DBHelper = GuestDatabaseHelper.get(mAppContext);

    }

    public static CookingSessionBank get(Context c) {

        if (sCookingSessionBank == null) {
            sCookingSessionBank = new CookingSessionBank(c.getApplicationContext());
        }
        return sCookingSessionBank;

    }

    public ArrayList<CookingSession> getCookingSessions() {
        return mCookingSessions;
    }



    public void addCookingSession(CookingSession cookingSession){
        mCookingSessions.add(cookingSession);
    }
    public String checkRecipe(long coose_id){
        GuestDatabaseHelper.IngrURICursor cursor =DBHelper.queryLIKEIngr(coose_id);
        cursor.moveToFirst();


        String api_id = "_app_id=249048de";
        String api_key = "&_app_key=42bde170d1a90836d7229a759f9e9fe9";
        String queryPrePara = "&q=";

        String _allowedIngr = "&allowedIngredient[]=";
        String _allowedIngrResult = "";
        String QUERY_RECIPE_KEY =  "q=";
        String ALLOWEDINGR_KEY = "&allowedIngredient[]=";


        Uri.Builder uri = new Uri.Builder();
        uri.scheme("http");
        uri.authority("api.yummly.com");
        uri.appendPath("v1");
        uri.appendPath("api");
        uri.encodedFragment("recipes?"); // // TODO: 12/08/15 If other query are needed this parameter has to be edited 

         // TODO: 12/08/15 Variable String to put in user input
        String recipe= "";
        try{
            recipe = URLEncoder.encode("onion soup", "UTF-8"); // todo encode the user input to URL standards
        }
        catch (Exception e)
        {
            // TOAST to user
        }

        uri.encodedFragment(api_id);
        uri.encodedFragment(api_key);
        uri.encodedFragment(queryPrePara);
        uri.fragment(recipe);

        while (cursor.moveToNext()){
            Log.i(TAG, cursor.getINGR());

        uri.encodedFragment(Uri.decode(_allowedIngr + cursor.getINGR()));
            _allowedIngrResult = _allowedIngrResult + _allowedIngr + cursor.getINGR();
            Log.e(TAG, _allowedIngrResult);

        }

        cursor.close();

        String keyID ="recipes?"+api_id+api_key;
        String scheme = "http://api.yummly.com/v1/api/";
        String Uri = scheme + keyID +queryPrePara +recipe + _allowedIngrResult;
        Log.e(TAG, Uri);


        uri.appendEncodedPath(keyID+ recipe + _allowedIngrResult);

        String uriString = uri.build().toString();
        Log.e(TAG, "URI: " + uriString);

        return Uri;
    }

}

