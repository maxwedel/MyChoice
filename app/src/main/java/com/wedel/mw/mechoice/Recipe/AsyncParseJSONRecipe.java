package com.wedel.mw.mechoice.Recipe;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.spec.ECField;
import java.util.ArrayList;

/**
 * Created by mw on 13/08/15.
 */
public class AsyncParseJSONRecipe extends AsyncTask<String, String, String> {

    final String TAG = "AsyncTaskParseJson.java";
    private String url;

    private static final String SOURCE_DISPLAY_NAME = "sourceDisplayName";


    // set your json string url here


    // contacts JSONArray
    JSONArray dataJsonArr = null;
    JSONArray ingrJsonArr = null;
    ArrayList<RecipeListItem> recipeResultArr;

    public boolean isReady=false;

    @Override
    protected void onPreExecute() {
    }

    @Override
    public String doInBackground(String... arg0) {

        // instantiate our json parser
        JsonServiceHandler jParser = new JsonServiceHandler();

        // get json string from url
        JSONObject json = jParser.makeHttpRequest(url, "GET");


        if (json != null) {
            try {
                recipeResultArr = new ArrayList<>();
                //new RecipeListItem
                RecipeListItem recipe = new RecipeListItem();
                // get the array of users
                dataJsonArr = json.getJSONArray("matches");

                // loop through all users
                for (int i = 0; i < dataJsonArr.length(); i++) {


                    JSONObject c = dataJsonArr.getJSONObject(i);


                    // bysize image url
                    JSONObject imageURLsBySize = null;
                    try {
                        imageURLsBySize = c.getJSONObject("imageUrlsBySize");
                    } catch (Exception e) {
                        Log.e(TAG, " no imageURLsBySize" + e.toString());
                    }
                    if (imageURLsBySize != null)
                        recipe.setImageUrlsBySize(imageURLsBySize.getString("90"));


                    // Storing each json item in variable
                    recipe.setSourceDisplayName(c.getString(SOURCE_DISPLAY_NAME));

                    // JSONArray with ingredients store in ArrayList
                    ingrJsonArr = c.getJSONArray("ingredients");
                    if (ingrJsonArr != null) {

                        ArrayList<String> ingrArrList = new ArrayList<>();

                        ingrArrList.add(ingrJsonArr.toString());
                        recipe.setIngrArr(ingrArrList);
                    }

                    recipe.setRecipeId(c.getString("id"));

                    // SmallPicture with ingredients store in ArrayList
                    JSONArray smallImageJsonArr = null;
                    try {
                        smallImageJsonArr = c.getJSONArray("smallImageUrls");
                    } catch (Exception e) {
                        Log.e(TAG, " No smallImageJsonArr" + e.toString());
                    }

                    if (smallImageJsonArr != null) {

                        ArrayList<String> smallImageArrList = new ArrayList<>();
                        for (int in = 0; in < smallImageJsonArr.length(); in++) {
                            smallImageArrList.add(smallImageJsonArr.toString());
                        }
                        recipe.setSmallImageUrls(smallImageArrList);
                    }


                    recipe.setRecipeName(c.getString("recipeName"));
                    recipe.setTotalTimeInSeconds(c.getString("totalTimeInSeconds"));


                    JSONObject attribute = null;
                    try {
                        attribute = c.getJSONObject("attributes");
                    } catch (Exception e) {
                        Log.e(TAG, " no attributes" + e.toString());
                    }
                    if (attribute != null) {
                        JSONArray course = null;
                        try {
                            course = c.getJSONArray("course");
                        } catch (Exception e) {
                            Log.e(TAG, " No course " + e.toString());
                        }
                        if (course != null) {
                            ArrayList<String> courseArr = new ArrayList<>();
                            courseArr.add(course.toString());
                            recipe.setCourses(courseArr);
                        }
                        JSONArray cuisine = null;
                        try {
                            cuisine = c.getJSONArray("cuisine");

                        } catch (Exception e) {
                            Log.e(TAG, " No Coursie " + e.toString());
                        }
                        if (cuisine != null) {
                            ArrayList<String> cuisineArr = new ArrayList<>();
                            cuisineArr.add(cuisine.toString());
                            recipe.setCuisines(cuisineArr);
                        }
                    }


                    JSONObject flavors = null;
                    try {
                        flavors = c.getJSONObject("flavors");
                    } catch (Exception e) {
                        Log.e(TAG, " no Flavors" + e.toString());
                    }
                    if (flavors != null) {

                        recipe.setFlavorPiquant(flavors.getInt("piquant"));
                        recipe.setFlavorMeaty(flavors.getInt("meaty"));
                        recipe.setFlavorBitter(flavors.getInt("bitter"));
                        recipe.setFlavorSweet(flavors.getInt("sweet"));
                        recipe.setFlavorSour(flavors.getInt("sour"));
                        recipe.setFlavorSalty(flavors.getInt("salty"));

                        recipe.setRating(c.getInt("rating"));
                    }

                    // concatenate to complete recipe list
                    recipeResultArr.add(recipe);

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return null;

    }

    @Override
    protected void onPostExecute(String strFromDoInBg) {
        isReady = true;

    }



    public void setURL(String url) {

        this.url = url;
    }
    public  ArrayList<RecipeListItem> getRecArr(){
        return recipeResultArr;
    }


}

