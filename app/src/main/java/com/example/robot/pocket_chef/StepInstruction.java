package com.example.robot.pocket_chef;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by Robot on 2/4/2018.
 */

public class StepInstruction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_instruction);

        Bundle b = new Bundle();
        b.putInt("recipeId", getIntent().getIntExtra("recipeId", -1));
        b.putInt("descriptionPos", getIntent().getIntExtra("descriptionPos", -1));

        StepInstructionFragment stepInstructionFragment = new StepInstructionFragment();
        stepInstructionFragment.setArguments(b);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.step_instruction_fragment_container, stepInstructionFragment).commit();

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
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
