package com.example.robot.pocket_chef.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.robot.pocket_chef.R;
import com.example.robot.pocket_chef.adapters.StepPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepViewPagerFragment extends Fragment {

    private final static String FRAGMENT_SELECTOR_ARG = "fragmentSelector";

    private final static int INGREDIENT_SELECTOR_ARG = 1;

    private final static int STEP_INSTRUCTION_SELECTOR_ARG = 0;

    private final static String RECIPE_ID_ARG = "recipeId";

    private final static String DESCRIPTION_POSITION = "descriptionPos";

    private final static int INGREDIENT_OFFSET = 1;

    private int mRecipeId;

    private int mDescriptionPos;

    public StepViewPagerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_step_view_pager, null);

        int fragmentSelector = getArguments().getInt(FRAGMENT_SELECTOR_ARG, 0);

        mRecipeId = getArguments().getInt(RECIPE_ID_ARG, 0);

        ViewPager pager = view.findViewById(R.id.step_viewpager);

        pager.setAdapter(new StepPagerAdapter(getChildFragmentManager(), mRecipeId));

        TabLayout tabLayout = view.findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(pager);

        switch (fragmentSelector){
            case STEP_INSTRUCTION_SELECTOR_ARG:
                mDescriptionPos = getArguments().getInt(DESCRIPTION_POSITION, 0);
                mDescriptionPos = mDescriptionPos + INGREDIENT_OFFSET;
                break;

            case INGREDIENT_SELECTOR_ARG:
                mDescriptionPos = getArguments().getInt(DESCRIPTION_POSITION, 0);
                mDescriptionPos = mDescriptionPos - INGREDIENT_OFFSET;
                break;
        }
        pager.setCurrentItem(mDescriptionPos, true);

        return view;

    }

}
