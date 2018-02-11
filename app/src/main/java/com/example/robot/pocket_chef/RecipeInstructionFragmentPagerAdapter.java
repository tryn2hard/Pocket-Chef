package com.example.robot.pocket_chef;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.robot.pocket_chef.dummy.DummyContent;

import java.util.List;

/**
 * Created by Robot on 2/11/2018.
 */

public class RecipeInstructionFragmentPagerAdapter extends FragmentPagerAdapter {

    private final static int INGREDIENT_FRAGMENT_OFFSETTING = 1;

    private List<DummyContent.Step> stepsToDisplay;
    private int mRecipeId;

    public RecipeInstructionFragmentPagerAdapter(FragmentManager fm, List<DummyContent.Step> stepsToDisplay, int recipeId) {
        super(fm);
        this.stepsToDisplay = stepsToDisplay;
        mRecipeId = recipeId;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return IngredientsFragment.newInstance(mRecipeId);
        } else {
            return StepInstructionFragment.newInstance(mRecipeId, position - INGREDIENT_FRAGMENT_OFFSETTING);
        }
    }

    @Override
    public int getCount() {
        return DummyContent.ITEMS.get(mRecipeId).steps.size() + INGREDIENT_FRAGMENT_OFFSETTING;
    }
}
