package com.example.robot.pocket_chef;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.robot.pocket_chef.dummy.DummyContent;




/**
 * A fragment representing a list of Items.
 * interface.
 */
public class RecipeDetailFragment extends Fragment implements
        RecipeDetailRecyclerViewAdapter.DetailAdapterOnClickHandler{

    // TODO: Customize parameter argument names
    private static final String ARG_RECIPE_ID = "recipeId";
    private static final String TAG = RecipeDetailFragment.class.getSimpleName();
    // TODO: Customize parameters
    private int mColumnCount = 1;

    private int mRecipeId; 


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RecipeDetailFragment() {
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
        View view = inflater.inflate(R.layout.fragment_recipe_detail_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new RecipeDetailRecyclerViewAdapter(DummyContent.ITEMS, mRecipeId, this));
        }
        return view;
    }

    @Override
    public void onClick(int id) {
        Log.d(RecipeDetailFragment.class.getSimpleName(), "Is this click going through?");
        Intent stepsIntent = new Intent(getActivity(), Steps.class);
        startActivity(stepsIntent);
    }

}
