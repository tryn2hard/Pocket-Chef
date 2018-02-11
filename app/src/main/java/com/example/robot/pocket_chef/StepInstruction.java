package com.example.robot.pocket_chef;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Switch;

import com.example.robot.pocket_chef.dummy.DummyContent;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_instruction);

        int fragmentSelector = getIntent().getIntExtra(FRAGMENT_SELECTOR_ARG, 0);

        switch (fragmentSelector){
            case STEP_INSTRUCTION_SELECTOR_ARG:
                Bundle b = new Bundle();
                b.putInt(RECIPE_ID_ARG, getIntent().getIntExtra(RECIPE_ID_ARG, -1));
                b.putInt(DESCRIPTION_POSITION, getIntent().getIntExtra(DESCRIPTION_POSITION, -1));
                StepInstructionFragment stepInstructionFragment = new StepInstructionFragment();
                stepInstructionFragment.setArguments(b);

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.step_instruction_fragment_container, stepInstructionFragment).commit();
                break;

            case INGREDIENT_SELECTOR_ARG:
                Bundle ingredientsData = new Bundle();
                ingredientsData.putInt(RECIPE_ID_ARG, getIntent().getIntExtra(RECIPE_ID_ARG, -1));
                IngredientsFragment ingredientsFragment = new IngredientsFragment();
                ingredientsFragment.setArguments(ingredientsData);

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.step_instruction_fragment_container, ingredientsFragment).commit();
                break;
        }

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
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, StepDescription.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
