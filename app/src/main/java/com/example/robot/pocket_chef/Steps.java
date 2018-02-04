package com.example.robot.pocket_chef;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Robot on 2/4/2018.
 */

public class Steps extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        Fragment stepsFragment = fm.findFragmentById(R.id.steps_fragment_container);

        if(stepsFragment == null){
            stepsFragment = new StepsFragment();
            fm.beginTransaction()
                    .add(R.id.steps_fragment_container, stepsFragment)
                    .commit();
        }
    }
}
