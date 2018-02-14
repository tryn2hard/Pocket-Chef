package com.example.robot.pocket_chef.activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.robot.pocket_chef.R;
import com.example.robot.pocket_chef.data.TestData;
import com.example.robot.pocket_chef.fragments.StepViewPagerFragment;

import java.util.List;

/**
 * Created by Robot on 2/14/2018.
 */

public class StepInstructionActivity extends AppCompatActivity {

    private final static String TAG = StepInstructionActivity.class.getSimpleName();

    private final static String FRAGMENT_SELECTOR_ARG = "fragmentSelector";

    private final static String RECIPE_ID_ARG = "recipeId";

    private int mRecipeId;

    private Bundle mExtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_instruction);

        mExtras = new Bundle();

        int fragmentSelector = getIntent().getIntExtra(FRAGMENT_SELECTOR_ARG, 0);

        mRecipeId = getIntent().getIntExtra(RECIPE_ID_ARG, 0);

        mExtras.putInt(RECIPE_ID_ARG, mRecipeId);

        mExtras.putInt(FRAGMENT_SELECTOR_ARG, fragmentSelector);

        StepViewPagerFragment newFragment = new StepViewPagerFragment();
        newFragment.setArguments(mExtras);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.step_view_pager_fragment_container, newFragment)
                .commit();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(
                     TestData.ITEMS.get(getIntent()
                            .getIntExtra(RECIPE_ID_ARG, -1)).recipeName);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {

            navigateUpTo(new Intent(this, StepDescriptionActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);

    }


}