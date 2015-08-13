package com.wedel.mw.mechoice.Recipe;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wedel.mw.mechoice.R;

import java.util.ArrayList;

/**
 * Created by mw on 13/08/15.
 */
public class RecipeListFragment extends ListFragment{

    private static final String TAG = "RecipeListFragment";
    public String mUrl = "";
    private ArrayList<RecipeListItem> mRecipeArr;
    private AsyncParseJSONRecipe parseJSONRecipe;

    public static  RecipeListFragment newInstance(){

        Bundle args = new Bundle();

        RecipeListFragment fragment = new RecipeListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Called to do initial creation of a fragment.  This is called after
     * {@link #onAttach(Activity)} and before
     * {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * <p/>
     * <p>Note that this can be called while the fragment's activity is
     * still in the process of being created.  As such, you can not rely
     * on things like the activity's content view hierarchy being initialized
     * at this point.  If you want to do work once the activity itself is
     * created, see {@link #onActivityCreated(Bundle)}.
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        parseJSONRecipe = new AsyncParseJSONRecipe();
        parseJSONRecipe.setURL("http://api.yummly.com/v1/api/recipes?_app_id=249048de&_app_key=42bde170d1a90836d7229a759f9e9fe9&q=onion+soup");

            parseJSONRecipe.execute();

            while (parseJSONRecipe.isReady == false){
                Log.d(TAG, "wating... recipe download");
            }
            mRecipeArr = parseJSONRecipe.getRecArr();

            RecipeItemAdapter adapter = new RecipeItemAdapter(mRecipeArr);
            setListAdapter(adapter);

        }

    /**
     * Attach to list view once the view hierarchy has been created.
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle agrs = getArguments();

    }

    public class RecipeItemAdapter extends ArrayAdapter<RecipeListItem>{

        public RecipeItemAdapter(ArrayList<RecipeListItem> recipes){
            super(getActivity(), 0, recipes);
        }

        /**
         * {@inheritDoc}
         *
         * @param position
         * @param convertView
         * @param parent
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView == null){
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_recipes, parent, false);
            }
            RecipeListItem r = getItem(position);

            TextView recipeName = (TextView)convertView
                    .findViewById(R.id.list_recipes_recipename_textView);
            try{
                recipeName.setText(r.getRecipeName());
            }catch (Exception e){
                recipeName.setText(r.getSourceDisplayName());
            }

            TextView ingr = (TextView)convertView
                    .findViewById(R.id.list_recipes_ingredients_textView);
            ingr.setText(r.ingrTostring());


            ImageView imageView = (ImageView)convertView.findViewById(R.id.list_recipes_imageView);
            ProvThumbNailLoader pro= new ProvThumbNailLoader();
            pro.setUrl(r.firstSmallUrlImage());
            pro.execute();

            while(pro.isReady == false)
            {
                Log.d(TAG, "wating....for Thumbnailloader");
            }
            imageView.setImageBitmap(pro.getBitmap());
            return convertView;
        }
    }
}
