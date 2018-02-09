package com.example.robot.pocket_chef;

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
public class StepInstructionFragment extends Fragment {

    private static final String ARG_RECIPE_ID = "recipeId";
    private static final String ARG_DESCRIPTION_POS = "descriptionPos";

    private int mRecipeId;
    private int mStepDescriptionPos;

    public StepInstructionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mRecipeId = getArguments().getInt(ARG_RECIPE_ID);
            mStepDescriptionPos = getArguments().getInt(ARG_DESCRIPTION_POS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_step_instruction, container, false);
        TextView textView = (TextView) view.findViewById(R.id.step_instruction_text_view);
        if(mRecipeId == -1 && mStepDescriptionPos == -1){
            textView.setText("Lets Start Baking!");
        }else {
            textView.setText(DummyContent.ITEMS.get(mRecipeId).steps.get(mStepDescriptionPos).description);
        }
        return view;
    }

}
