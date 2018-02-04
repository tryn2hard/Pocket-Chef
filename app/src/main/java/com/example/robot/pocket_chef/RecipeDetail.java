package com.example.robot.pocket_chef;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.robot.pocket_chef.dummy.DummyContent;

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

    }

}
