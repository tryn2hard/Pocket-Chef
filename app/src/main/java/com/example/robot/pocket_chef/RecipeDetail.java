package com.example.robot.pocket_chef;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by Robot on 2/4/2018.
 */

public class RecipeDetail extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        Fragment detailFragment = fm.findFragmentById(R.id.detail_fragment_container);

        if(detailFragment == null){
            detailFragment = new RecipeDetailFragment();
            fm.beginTransaction()
                    .add(R.id.detail_fragment_container, detailFragment)
                    .commit();
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
