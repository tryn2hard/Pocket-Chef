package com.example.robot.pocket_chef;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

/**
 * Created by Robot on 2/4/2018.
 */

public class RecipeDetail extends AppCompatActivity  {

    private final static String TAG = RecipeDetail.class.getSimpleName();

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Bundle b = new Bundle();
        b.putInt("recipeId", getIntent().getIntExtra("recipeId", 1));

        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
        recipeDetailFragment.setArguments(b);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.detail_list_fragment, recipeDetailFragment).commit();

        if(findViewById(R.id.pocket_chef_linear_layout) != null){
            mTwoPane = true;

            if(savedInstanceState == null) {

                // In two-pane mode

                StepsFragment stepsFragment = new StepsFragment();

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.steps_fragment_container, stepsFragment)
                        .commit();
            }
        }

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, Recipes.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
