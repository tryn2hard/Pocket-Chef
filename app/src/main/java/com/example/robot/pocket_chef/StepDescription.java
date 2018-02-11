package com.example.robot.pocket_chef;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.robot.pocket_chef.dummy.DummyContent;


/**
 * Created by Robot on 2/4/2018.
 */

public class StepDescription extends AppCompatActivity implements
        StepDescriptionFragment.OnDescriptionClickListener {

    private final static String TAG = StepDescription.class.getSimpleName();

    private final static String FRAGMENT_SELECTOR_ARG = "fragmentSelector";

    private final static int INGREDIENT_SELECTOR_ARG = 1;

    private final static int STEP_INSTRUCTION_SELECTOR_ARG = 0;

    private final static String RECIPE_ID_ARG = "recipeId";

    private final static String DESCRIPTION_POSITION = "descriptionPos";

    private boolean mTwoPane;

    private int mRecipeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_description);

        if (getIntent() != null) {
            mRecipeId = getIntent().getIntExtra(RECIPE_ID_ARG, 0);
        }
        Bundle b = new Bundle();
        b.putInt(RECIPE_ID_ARG, mRecipeId);
        StepDescriptionFragment stepDescriptionFragment = new StepDescriptionFragment();
        stepDescriptionFragment.setArguments(b);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.step_description_list_fragment_container, stepDescriptionFragment).commit();

        if (findViewById(R.id.pocket_chef_linear_layout) != null) {
            mTwoPane = true;

            if (savedInstanceState == null) {

                Bundle intialData = new Bundle();
                intialData.putInt(RECIPE_ID_ARG, -1);
                intialData.putInt(DESCRIPTION_POSITION, -1);
                intialData.putInt(FRAGMENT_SELECTOR_ARG, STEP_INSTRUCTION_SELECTOR_ARG);

                // In two-pane mode
                StepInstructionFragment stepInstructionFragment = new StepInstructionFragment();
                stepInstructionFragment.setArguments(intialData);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.step_instruction_fragment_container, stepInstructionFragment)
                        .commit();
            }
        } else {
            mTwoPane = false;
        }

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(DummyContent.ITEMS.get(mRecipeId).recipeName);
        }

    }

    public void onDescriptionSelected(int stepDescriptionPos) {

        Toast.makeText(this, "Position clicked = " + stepDescriptionPos, Toast.LENGTH_SHORT).show();

        Bundle b = new Bundle();
        b.putInt(RECIPE_ID_ARG, mRecipeId);
        b.putInt(DESCRIPTION_POSITION, stepDescriptionPos);
        b.putInt(FRAGMENT_SELECTOR_ARG, STEP_INSTRUCTION_SELECTOR_ARG);

        if (mTwoPane) {
            StepInstructionFragment newFragment = new StepInstructionFragment();
            newFragment.setArguments(b);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.step_instruction_fragment_container, newFragment)
                    .commit();
        } else {

            Intent stepsIntent = new Intent(this, StepInstruction.class);
            stepsIntent.putExtras(b);
            startActivity(stepsIntent);
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

    public void ingredientsOnclick(View view){
        Bundle b = new Bundle();
        b.putInt(RECIPE_ID_ARG, mRecipeId);
        b.putInt(FRAGMENT_SELECTOR_ARG, INGREDIENT_SELECTOR_ARG);

        if (mTwoPane) {
            IngredientsFragment newFragment = new IngredientsFragment();
            newFragment.setArguments(b);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.step_instruction_fragment_container, newFragment)
                    .commit();
        } else {
            Intent stepsIntent = new Intent(this, StepInstruction.class);
            stepsIntent.putExtras(b);
            startActivity(stepsIntent);
        }
    }

}
