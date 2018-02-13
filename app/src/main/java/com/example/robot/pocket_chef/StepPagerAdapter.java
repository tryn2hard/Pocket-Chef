package com.example.robot.pocket_chef;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.robot.pocket_chef.data.TestData;

/**
 * Created by Robot on 2/11/2018.
 */

public class StepPagerAdapter extends FragmentPagerAdapter {

    private final static int INGREDIENT_FRAGMENT_OFFSET = 1;

    private int mRecipeId;

    public StepPagerAdapter(FragmentManager fm, int recipeId) {
        super(fm);
        mRecipeId = recipeId;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return IngredientsFragment.newInstance(mRecipeId);
        } else {
            return StepInstructionFragment.newInstance(mRecipeId, position - INGREDIENT_FRAGMENT_OFFSET);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {

        if(position == 0){
            return "Ingredients";
        } else {
            return "Step " + position;
        }
    }

    @Override
    public int getCount() {
        return TestData.ITEMS.get(mRecipeId).steps.size() + INGREDIENT_FRAGMENT_OFFSET;
    }
}
