package com.wedel.mw.mechoice.Recipe;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by mw on 13/08/15.
 */
public class RecipeListItem {


    private String imageUrlsBySize;
    private String sourceDisplayName;
    private String recipeId;
    private String recipeName;
    private String totalTimeInSeconds;

    private ArrayList<String> ingrArr;
    private ArrayList<String> courses;
    private ArrayList<String> cuisines;
    private ArrayList<String> smallImageUrls;

    private int flavorPiquant;
    private int flavorMeaty;
    private int flavorBitter;
    private int flavorSweet;
    private int flavorSour;
    private int flavorSalty;


    private int rating;


    private static final String TAG = "RecipeListItem: ";
    public RecipeListItem() {
    }

    public String ingrTostring(){
        String result ="";
        for (String s: ingrArr) {
            result = result + s + ", ";
        }
        return result;
    }

    public String firstSmallUrlImage(){
        for (String s:smallImageUrls
             ) {
            return s;
        }
        return null;
    }

    public String getImageUrlsBySize() {
        return imageUrlsBySize;
    }

    public void setImageUrlsBySize(String imageUrlsBySize) {
        this.imageUrlsBySize = imageUrlsBySize;
        Log.e(TAG, imageUrlsBySize);
    }

    public String getSourceDisplayName() {
        return sourceDisplayName;
    }

    public void setSourceDisplayName(String sourceDisplayName) {
        this.sourceDisplayName = sourceDisplayName;
        Log.e(TAG, sourceDisplayName);
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
        Log.e(TAG, recipeName);
    }

    public String getTotalTimeInSeconds() {
        return totalTimeInSeconds;
    }

    public void setTotalTimeInSeconds(String totalTimeInSeconds) {
        this.totalTimeInSeconds = totalTimeInSeconds;
    }

    public ArrayList<String> getIngrArr() {
        return ingrArr;
    }

    public void setIngrArr(ArrayList<String> ingrArr) {
        this.ingrArr = ingrArr;
        for(String i: ingrArr){
            Log.e(TAG, "ingr: " + i);
        }
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<String> courses) {
        this.courses = courses;
    }

    public ArrayList<String> getCuisines() {
        return cuisines;
    }

    public void setCuisines(ArrayList<String> cuisines) {
        this.cuisines = cuisines;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public ArrayList<String> getSmallImageUrls() {
        return smallImageUrls;
    }

    public void setSmallImageUrls(ArrayList<String> smallImageUrls) {
        this.smallImageUrls = smallImageUrls;
        for(String i: smallImageUrls){
            Log.e(TAG, "smallImageUrls: " + i);}
    }

    public int getFlavorPiquant() {
        return flavorPiquant;
    }

    public void setFlavorPiquant(int flavorPiquant) {
        this.flavorPiquant = flavorPiquant;
    }

    public int getFlavorMeaty() {
        return flavorMeaty;
    }

    public void setFlavorMeaty(int flavorMeaty) {
        this.flavorMeaty = flavorMeaty;
    }

    public int getFlavorBitter() {
        return flavorBitter;
    }

    public void setFlavorBitter(int flavorBitter) {
        this.flavorBitter = flavorBitter;
    }

    public int getFlavorSweet() {
        return flavorSweet;
    }

    public void setFlavorSweet(int flavorSweet) {
        this.flavorSweet = flavorSweet;
    }

    public int getFlavorSour() {
        return flavorSour;
    }

    public void setFlavorSour(int flavorSour) {
        this.flavorSour = flavorSour;
    }

    public int getFlavorSalty() {
        return flavorSalty;
    }

    public void setFlavorSalty(int flavorSalty) {
        this.flavorSalty = flavorSalty;

    }
}
