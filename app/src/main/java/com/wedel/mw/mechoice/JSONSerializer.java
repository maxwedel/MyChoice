package com.wedel.mw.mechoice;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.wedel.mw.mechoice.Guest.GuestDatabaseHelper;
import com.wedel.mw.mechoice.guest_taste.Ingr;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The JSONSerializer reads ingredients, cuisine, diet, allergy, course and holiday information
 * on the first load of the application on a new smartphone
 */
public class JSONSerializer extends AsyncTaskLoader {


    Context context;
    GuestDatabaseHelper DBHelper;

    private static final String TAG = "JSONSerializer";

//    public static String Ingr_DB_Creation;
//    public static String Alrgy_DB_Creation;
//    public static String Diet_DB_Creation;
//    public static String Course_DB_Creation;
//    public static String Hlday_DB_Creation;
//    public static String Cuisine_DB_Creation;

    public JSONSerializer(Context context) {
        super(context);
        DBHelper = GuestDatabaseHelper.get(context);
    }

    @Override
    public Object loadInBackground() {

        readIngrJSON();
        readAlrgyJSON();
        readDietJSON();
        readHolidayJSON();
        readCourseJSON();
        readCuisineJSON();
        return null;
    }

    /**
     * @param filename
     * @return String json read from the filename
     */
    public String loadJSONFromAsset(String filename) {
        String json = null;

        try {

            InputStream is = getContext().getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    public void readIngrJSON() {
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset("ingr.json"));
            JSONArray m_jArry = obj.getJSONArray("Ingredients");


            for (int i = 0; i <40; i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                Log.d(TAG, i + " Parsed ingr: " + jo_inside.getString("searchValue"));
                String search_value = jo_inside.getString("searchValue");
                String description = jo_inside.getString("description");
                String term = jo_inside.getString("term");

                DBHelper.insertIngr(new Ingr(search_value,description,term));

            }
        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    private void readAlrgyJSON() {
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset("alrgy.json"));
            JSONArray m_jArry = obj.getJSONArray("allergy");


            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                Log.d(TAG, "Parsed alrgy: " + jo_inside.getString("shortDescription"));

                int alrgy_id = jo_inside.getInt("id");
                String shortDescription = jo_inside.getString("shortDescription");
                String longDescription = jo_inside.getString("longDescription");
                String searchValue = jo_inside.getString("searchValue");
                // left "type" and "localesAvailablIn"

            }
        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    private void readDietJSON() {
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset("diet.json"));
            JSONArray m_jArry = obj.getJSONArray("diet");


            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                Log.d(TAG, "Parsed diet: " + jo_inside.getString("shortDescription"));

                int diet_id = jo_inside.getInt("id");
                String shortDescription = jo_inside.getString("shortDescription");
                String longDescription = jo_inside.getString("longDescription");
                String searchValue = jo_inside.getString("searchValue");
                // left "type" and "localesAvailablIn"


            }
        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    private void readHolidayJSON() {
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset("holiday.json"));
            JSONArray m_jArry = obj.getJSONArray("holiday");

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                Log.d(TAG, "Parsed holiday: " + jo_inside.getString("name"));

                String Hlday_yummli_id = jo_inside.getString("id");
                String name = jo_inside.getString("name");
                String description = jo_inside.getString("description");
                String searchValue = jo_inside.getString("searchValue");
                // left "type" and "localesAvailablIn"




            }
        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    private void readCourseJSON() {
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset("course.json"));
            JSONArray m_jArry = obj.getJSONArray("course");


            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                Log.d(TAG, "Parsed cours: " + jo_inside.getString("name"));

                String course_yummli_id = jo_inside.getString("id");
                String name = jo_inside.getString("name");
                String description = jo_inside.getString("description");
                String searchValue = jo_inside.getString("searchValue");
                // left "type" and "localesAvailablIn"



            }
        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    private void readCuisineJSON() {
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset("cuisine.json"));
            JSONArray m_jArry = obj.getJSONArray("cuisine");


            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                Log.d(TAG, "Parsed cuisine: " + jo_inside.getString("name"));

                String cuisine_yummli_id = jo_inside.getString("id");
                String name = jo_inside.getString("name");
                String description = jo_inside.getString("description");
                String searchValue = jo_inside.getString("searchValue");
                // left "type" and "localesAvailablIn"


            }
        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

}