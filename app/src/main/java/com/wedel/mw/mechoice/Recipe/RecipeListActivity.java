package com.wedel.mw.mechoice.Recipe;

import android.os.NetworkOnMainThreadException;
import android.support.v4.app.Fragment;

import com.wedel.mw.mechoice.SingleFragmentActivity;

/**
 * Created by mw on 13/08/15.
 */
public class RecipeListActivity extends SingleFragmentActivity {

    public static final String EXTRA_URI= "com.wedel.mechoice.recipelist.uri";

    @Override
    protected Fragment createFragment() {
        AsyncParseJSONRecipe parseJSONRecipe = new AsyncParseJSONRecipe();
        parseJSONRecipe.setURL("http://api.yummly.com/v1/api/recipes?_app_id=249048de&_app_key=42bde170d1a90836d7229a759f9e9fe9&q=onion+soup");

        parseJSONRecipe.execute();
        return RecipeListFragment.newInstance();
    }
}
