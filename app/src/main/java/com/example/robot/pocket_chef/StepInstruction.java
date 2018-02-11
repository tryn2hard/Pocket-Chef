package com.example.robot.pocket_chef;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.robot.pocket_chef.dummy.DummyContent;

import java.util.List;

/**
 * Created by Robot on 2/4/2018.
 */

public class StepInstruction extends AppCompatActivity {

    private final static String TAG = StepDescription.class.getSimpleName();

    private final static String FRAGMENT_SELECTOR_ARG = "fragmentSelector";

    private final static int INGREDIENT_SELECTOR_ARG = 1;

    private final static int STEP_INSTRUCTION_SELECTOR_ARG = 0;

    private final static String RECIPE_ID_ARG = "recipeId";

    private final static String DESCRIPTION_POSITION = "descriptionPos";

    private final static int INGREDIENT_OFFSETTING = 1;

    private int mRecipeId;

    private int mDescriptionPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_instruction);

        int fragmentSelector = getIntent().getIntExtra(FRAGMENT_SELECTOR_ARG, 0);

        mRecipeId = getIntent().getIntExtra(RECIPE_ID_ARG, 0);
        List<DummyContent.Step> stepList = DummyContent.ITEMS.get(mRecipeId).steps;
        ViewPager pager = findViewById(R.id.step_instruction_viewpager);
        pager.setAdapter(new RecipeInstructionFragmentPagerAdapter(getSupportFragmentManager(),stepList, mRecipeId ));


        switch (fragmentSelector){
            case STEP_INSTRUCTION_SELECTOR_ARG:
                mDescriptionPos = getIntent().getIntExtra(DESCRIPTION_POSITION, 0);
                mDescriptionPos = mDescriptionPos + INGREDIENT_OFFSETTING;
                break;

            case INGREDIENT_SELECTOR_ARG:
                mDescriptionPos = getIntent().getIntExtra(DESCRIPTION_POSITION, 0);
                break;
        }
        pager.setCurrentItem(mDescriptionPos, true);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(
                    DummyContent.ITEMS.get(getIntent()
                            .getIntExtra(RECIPE_ID_ARG, -1)).recipeName);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button.
            navigateUpTo(new Intent(this, StepDescription.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
