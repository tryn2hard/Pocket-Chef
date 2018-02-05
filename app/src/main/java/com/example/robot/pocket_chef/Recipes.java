package com.example.robot.pocket_chef;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Recipes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        Fragment recipeListFragment = fm.findFragmentById(R.id.recipe_list_fragment);

        if(recipeListFragment == null){
            recipeListFragment = new RecipesFragment();
            fm.beginTransaction()
                    .add(R.id.recipe_list_fragment, recipeListFragment)
                    .commit();
        }
    }

}
