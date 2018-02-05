package com.example.robot.pocket_chef;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by Robot on 2/4/2018.
 */

public class RecipeDetail extends AppCompatActivity  {

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        if(findViewById(R.id.pocket_chef_linear_layout) != null){
            mTwoPane = true;

            if(savedInstanceState == null) {

                // In two-pane mode
                FragmentManager fm = getSupportFragmentManager();

                StepsFragment stepsFragment = new StepsFragment();

                fm.beginTransaction()
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
