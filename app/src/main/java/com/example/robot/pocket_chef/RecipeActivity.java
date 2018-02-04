package com.example.robot.pocket_chef;

import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.robot.pocket_chef.dummy.DummyContent;

public class RecipeActivity extends AppCompatActivity implements RecipeFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        Fragment recipeListFragment = fm.findFragmentById(R.id.recipe_fragment_container);

        if(recipeListFragment == null){
            recipeListFragment = new RecipeFragment();
            fm.beginTransaction()
                    .add(R.id.recipe_fragment_container, recipeListFragment)
                    .commit();
        }
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
