package com.example.robot.pocket_chef.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.robot.pocket_chef.R;
import com.example.robot.pocket_chef.data.TestData;
import com.example.robot.pocket_chef.fragments.StepViewPagerFragment;

/**
 * Created by Robot on 2/14/2018.
 */

public class StepInstructionActivity extends AppCompatActivity {

    private final static String TAG = StepInstructionActivity.class.getSimpleName();
    private final static String ARG_RECIPE_ID = "recipeId";
    private final static String ARG_STEP_DESCRIPTION_POSITION = "descriptionPos";
    private final static String ARG_FRAGMENT_SELECTOR = "fragmentSelector";
    private final static String FRAGMENT_TAG = "StepViewPager";


    private int mRecipeId;
    private int mStepDescriptionPos;
    private Bundle mExtras;
    private StepViewPagerFragment mStepViewPagerFragment;
    private int mFragmentSelector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_instruction);

        mExtras = new Bundle();

        if(getIntent() != null) {
            mRecipeId = getIntent().getIntExtra(ARG_RECIPE_ID, 0);
            mStepDescriptionPos = getIntent().getIntExtra(ARG_STEP_DESCRIPTION_POSITION, 0);
            mFragmentSelector = getIntent().getIntExtra(ARG_FRAGMENT_SELECTOR, 0);
        }

        mExtras.putInt(ARG_RECIPE_ID, mRecipeId);
        mExtras.putInt(ARG_STEP_DESCRIPTION_POSITION, mStepDescriptionPos);
        mExtras.putInt(ARG_FRAGMENT_SELECTOR, mFragmentSelector);

        if (savedInstanceState == null){
            mStepViewPagerFragment = new StepViewPagerFragment();
            mStepViewPagerFragment.setArguments(mExtras);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.step_view_pager_fragment_container, mStepViewPagerFragment, FRAGMENT_TAG)
                    .commit();
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(TestData.ITEMS.get(getIntent().getIntExtra(ARG_RECIPE_ID, -1)).recipeName);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {

            Intent returnIntent = new Intent(this, StepDescriptionActivity.class);
            returnIntent.putExtras(mExtras);

            navigateUpTo(returnIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

}