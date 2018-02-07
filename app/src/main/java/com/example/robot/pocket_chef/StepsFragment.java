package com.example.robot.pocket_chef;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.robot.pocket_chef.dummy.DummyContent;


/**
 * A simple {@link Fragment} subclass.
 */
public class StepsFragment extends Fragment {

    private static final String ARG_RECIPE_ID = "recipeId";
    private static final String ARG_DESCRIPTION_POS = "descriptionPos";

    private int mRecipeId;

    private int mRecipeDescriptionPos;

    public StepsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mRecipeId = getArguments().getInt(ARG_RECIPE_ID);
            mRecipeDescriptionPos = getArguments().getInt(ARG_DESCRIPTION_POS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_steps, container, false);
        TextView textView = (TextView) view.findViewById(R.id.step_instruction);
        textView.setText(DummyContent.ITEMS.get(mRecipeId).stepInstruction.get(mRecipeDescriptionPos));
        return view;
    }

}
