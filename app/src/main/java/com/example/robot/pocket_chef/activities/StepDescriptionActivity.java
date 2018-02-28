package com.example.robot.pocket_chef.activities;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.robot.pocket_chef.R;
import com.example.robot.pocket_chef.fragments.StepDescriptionFragment;
import com.example.robot.pocket_chef.fragments.StepViewPagerFragment;
import com.example.robot.pocket_chef.data.TestData;
import com.example.robot.pocket_chef.widget.WidgetProvider;


/**
 * Created by Robot on 2/4/2018.
 */

public class StepDescriptionActivity extends AppCompatActivity implements
        StepDescriptionFragment.OnDescriptionClickListener {

    // Constants
    private final static String TAG = StepDescriptionActivity.class.getSimpleName();
    private final static String ARG_FRAGMENT_SELECTOR = "fragmentSelector";
    private final static int INGREDIENT_SELECTOR_CONSTANT = 1;
    private final static int STEP_INSTRUCTION_SELECTOR_CONSTANT = 0;
    private final static String ARG_RECIPE_ID = "recipeId";
    private final static String DESCRIPTION_POSITION = "descriptionPos";
    private final static int INGREDIENT_OFFSET = 1;

    // Member variables
    private boolean mTwoPane;
    private int mRecipeId;
    private Bundle mExtras;

    // Views
    private TextView mIngredientsView;
    private View mDividerView;
    private CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_description);

        mIngredientsView = findViewById(R.id.ingredients_text_view);
        mDividerView = findViewById(R.id.divider_view);

        if (getIntent() != null) {
            mRecipeId = getIntent().getIntExtra(ARG_RECIPE_ID, 0);
        }
        mExtras = new Bundle();
        mExtras.putInt(ARG_RECIPE_ID, mRecipeId);

        StepDescriptionFragment stepDescriptionFragment = new StepDescriptionFragment();
        stepDescriptionFragment.setArguments(mExtras);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.step_description_fragment_container, stepDescriptionFragment).commit();

        // Check to see if the user is using a tablet
        if (findViewById(R.id.pocket_chef_linear_layout) != null) {
            mTwoPane = true;
            Bundle mInitialTwoPaneExtras = new Bundle();

            if (savedInstanceState == null) {

                mInitialTwoPaneExtras.putInt(ARG_RECIPE_ID, mRecipeId);
                mInitialTwoPaneExtras.putInt(DESCRIPTION_POSITION, -INGREDIENT_OFFSET);
                mInitialTwoPaneExtras.putInt(ARG_FRAGMENT_SELECTOR, STEP_INSTRUCTION_SELECTOR_CONSTANT);
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

        mCheckBox = findViewById(R.id.follow_ingredient_checkBox);


        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(StepDescriptionActivity.this);

        int checkBoxStatus = prefs.getInt(ARG_RECIPE_ID, -1);

        if(checkBoxStatus == -1){
            mCheckBox.setChecked(false);
        } else if (checkBoxStatus == mRecipeId){
            mCheckBox.setChecked(true);
        }

        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor =
                        PreferenceManager.getDefaultSharedPreferences(StepDescriptionActivity.this).edit();

                if (isChecked) {

                    Toast.makeText(StepDescriptionActivity.this,
                            TestData.ITEMS.get(mRecipeId).recipeName +
                                    " ingredients list added to home widget",
                            Toast.LENGTH_LONG).show();

                    updateIngredientWidget(StepDescriptionActivity.this, mRecipeId);

                    editor.putInt(ARG_RECIPE_ID, mRecipeId);
                    editor.commit();

                } else {

                    editor.putInt(ARG_RECIPE_ID, -1);
                    editor.commit();
                }
            }
        });

    }

    public static void updateIngredientWidget(Context context, int recipeId) {
        Intent widgetIntent = new Intent(context.getApplicationContext(), WidgetProvider.class);
        widgetIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        AppWidgetManager widgetManager = AppWidgetManager.getInstance(context);
        int ids[] = widgetManager.getAppWidgetIds(
                new ComponentName(context, WidgetProvider.class));
        widgetIntent.putExtra(ARG_RECIPE_ID, recipeId);
        widgetIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        context.sendBroadcast(widgetIntent);
    }

    public void onDescriptionSelected(int stepDescriptionPos) {

        mExtras.putInt(ARG_RECIPE_ID, mRecipeId);
        mExtras.putInt(DESCRIPTION_POSITION, stepDescriptionPos);
        mExtras.putInt(ARG_FRAGMENT_SELECTOR, STEP_INSTRUCTION_SELECTOR_CONSTANT);

        if (mTwoPane) {
            loadViewPagerFragment(mExtras);
        } else {
            startStepInstructionIntent(mExtras);
        }

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

    public void ingredientsOnclick(View view) {

        mExtras.putInt(ARG_RECIPE_ID, mRecipeId);
        mExtras.putInt(ARG_FRAGMENT_SELECTOR, INGREDIENT_SELECTOR_CONSTANT);

        if (mTwoPane) {
            loadViewPagerFragment(mExtras);
        } else {

            startStepInstructionIntent(mExtras);
        }
    }

    public void loadViewPagerFragment(Bundle extras) {
        StepViewPagerFragment newFragment = new StepViewPagerFragment();
        newFragment.setArguments(extras);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.step_instruction_fragment_container, newFragment)
                .commit();

    }

    public void startStepInstructionIntent(Bundle extras) {
        Intent stepsIntent = new Intent(this, StepInstructionActivity.class);
        stepsIntent.putExtras(extras);
        startActivity(stepsIntent);
    }

}
