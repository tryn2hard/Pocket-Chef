package com.example.robot.pocket_chef.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.robot.pocket_chef.R;
import com.example.robot.pocket_chef.adapters.StepDescriptionRecyclerViewAdapter;
import com.example.robot.pocket_chef.data.RecipeData;




/**
 * A fragment representing a list of Items.
 * interface.
 */
public class StepDescriptionFragment extends Fragment implements
        StepDescriptionRecyclerViewAdapter.DetailAdapterOnClickHandler {


    private static final String ARG_RECIPE_ID = "recipeId";
    private static final String TAG = StepDescriptionFragment.class.getSimpleName();

    private int mColumnCount = 1;

    private int mRecipeId;

    OnDescriptionClickListener mDescriptionCallback;

    public interface OnDescriptionClickListener {
        void onDescriptionSelected(int position);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            mDescriptionCallback = (OnDescriptionClickListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString()
            + " must implement listeners");
        }
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public StepDescriptionFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mRecipeId = getArguments().getInt(ARG_RECIPE_ID);
            Log.d(TAG, "The value of recipeId is " + mRecipeId);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_description_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            recyclerView.setAdapter(new StepDescriptionRecyclerViewAdapter(RecipeData.RECIPES, mRecipeId, this));
        }
        return view;
    }

    @Override
    public void onClick(int stepDescriptionPos) {
        mDescriptionCallback.onDescriptionSelected(stepDescriptionPos);
    }


}
