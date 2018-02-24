package com.example.robot.pocket_chef.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.robot.pocket_chef.R;
import com.example.robot.pocket_chef.adapters.StepPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepViewPagerFragment extends Fragment {

    // Constants
    private final static String FRAGMENT_SELECTOR_ARG = "fragmentSelector";
    private final static String RECIPE_ID_ARG = "recipeId";
    private final static String DESCRIPTION_POSITION = "descriptionPos";

    private final static int INGREDIENT_SELECTOR_ARG = 1;
    private final static int STEP_INSTRUCTION_SELECTOR_ARG = 0;
    private final static int INGREDIENT_OFFSET = 1;

    // member variables
    private int mRecipeId;
    private int mDescriptionPos;
    private int mFragmentSelector;
    private ViewPager mPager;
    private StepPagerAdapter mAdapter;
    private Context mContext;

    public StepViewPagerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_step_view_pager, container, false);

        if (getArguments() != null) {
            mFragmentSelector = getArguments().getInt(FRAGMENT_SELECTOR_ARG, 0);
            mRecipeId = getArguments().getInt(RECIPE_ID_ARG, 0);
            mContext = getActivity();
        }

        mPager = rootView.findViewById(R.id.step_viewpager);
        mAdapter = new StepPagerAdapter(getChildFragmentManager(), mRecipeId);
        mPager.setAdapter(mAdapter);

        TabLayout tabLayout = rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mPager);

        mPager.setOffscreenPageLimit(1);

        offset(mFragmentSelector);

        return rootView;

    }


    private void offset(int fragmentSelector) {

        switch (fragmentSelector) {
            case STEP_INSTRUCTION_SELECTOR_ARG:
                mDescriptionPos = getArguments().getInt(DESCRIPTION_POSITION, 0);
                mDescriptionPos = mDescriptionPos + INGREDIENT_OFFSET;
                break;

            case INGREDIENT_SELECTOR_ARG:
                mDescriptionPos = getArguments().getInt(DESCRIPTION_POSITION, 0);
                mDescriptionPos = mDescriptionPos - INGREDIENT_OFFSET;
                break;
        }
        mPager.setCurrentItem(mDescriptionPos, true);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Fragment page = getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.step_viewpager + ":" + mPager.getCurrentItem());
            Log.d("ViewPager", "View pager position " + mPager.getCurrentItem());

            if (page instanceof StepInstructionFragment) {
                StepInstructionFragment currentFrag = (StepInstructionFragment) page;
                Context c = currentFrag.getContext();
                currentFrag.initFullscreenDialog(c);
                currentFrag.openFullscreenDialog();

            }
        }
    }


}
