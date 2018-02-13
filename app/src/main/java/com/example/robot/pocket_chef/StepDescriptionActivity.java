package com.example.robot.pocket_chef;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.robot.pocket_chef.data.TestData;


/**
 * Created by Robot on 2/4/2018.
 */

public class StepDescriptionActivity extends AppCompatActivity implements
        StepDescriptionFragment.OnDescriptionClickListener {

    private final static String TAG = StepDescriptionActivity.class.getSimpleName();
    private final static String FRAGMENT_SELECTOR_ARG = "fragmentSelector";
    private final static int INGREDIENT_SELECTOR_ARG = 1;
    private final static int STEP_INSTRUCTION_SELECTOR_ARG = 0;
    private final static String RECIPE_ID_ARG = "recipeId";
    private final static String DESCRIPTION_POSITION = "descriptionPos";
    private final static int INGREDIENT_OFFSET = 1;

    private boolean mTwoPane;
    private int mRecipeId;
    private Bundle mExtras;
    private TextView mIngredientsView;
    private View mDividerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_description);

        mIngredientsView = findViewById(R.id.ingredients_text_view);
        mDividerView = findViewById(R.id.divider_view);

        if (getIntent() != null) {
            mRecipeId = getIntent().getIntExtra(RECIPE_ID_ARG, 0);
        }
        mExtras = new Bundle();
        mExtras.putInt(RECIPE_ID_ARG, mRecipeId);
        StepDescriptionFragment stepDescriptionFragment = new StepDescriptionFragment();
        stepDescriptionFragment.setArguments(mExtras);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.step_description_fragment_container, stepDescriptionFragment).commit();

        if (findViewById(R.id.pocket_chef_linear_layout) != null) {
            mTwoPane = true;
            Bundle mInitialTwoPaneExtras = new Bundle();

            if (savedInstanceState == null) {

                mInitialTwoPaneExtras.putInt(RECIPE_ID_ARG, mRecipeId);
                mInitialTwoPaneExtras.putInt(DESCRIPTION_POSITION, - INGREDIENT_OFFSET );
                mInitialTwoPaneExtras.putInt(FRAGMENT_SELECTOR_ARG, STEP_INSTRUCTION_SELECTOR_ARG);
                loadViewPagerFragment(mInitialTwoPaneExtras);
            }
        } else {
            mTwoPane = false;
        }

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(TestData.ITEMS.get(mRecipeId).recipeName);
        }

    }

    public void onDescriptionSelected(int stepDescriptionPos) {

        Toast.makeText(this, "Position clicked = " + stepDescriptionPos, Toast.LENGTH_SHORT).show();

        mExtras.putInt(RECIPE_ID_ARG, mRecipeId);
        mExtras.putInt(DESCRIPTION_POSITION, stepDescriptionPos);
        mExtras.putInt(FRAGMENT_SELECTOR_ARG, STEP_INSTRUCTION_SELECTOR_ARG);

        loadViewPagerFragment(mExtras);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown.
            navigateUpTo(new Intent(this, RecipesActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void ingredientsOnclick(View view){

        mExtras.putInt(RECIPE_ID_ARG, mRecipeId);
        mExtras.putInt(FRAGMENT_SELECTOR_ARG, INGREDIENT_SELECTOR_ARG);

        if (mTwoPane) {
            StepViewPagerFragment newFragment = new StepViewPagerFragment();
            newFragment.setArguments(mExtras);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.step_instruction_fragment_container, newFragment)
                    .commit();
        } else {

            loadViewPagerFragment(mExtras);
        }
    }

    public void loadViewPagerFragment(Bundle extras){

        StepViewPagerFragment newFragment = new StepViewPagerFragment();
        newFragment.setArguments(extras);

        if(!mTwoPane) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.step_description_fragment_container, newFragment)
                    .commit();
            mIngredientsView.setVisibility(View.GONE);
            mDividerView.setVisibility(View.GONE);
        } else{
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.step_instruction_fragment_container, newFragment)
                    .commit();

        }
    }

}
