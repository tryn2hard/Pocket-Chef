package com.example.robot.pocket_chef;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.robot.pocket_chef.dummy.DummyContent;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class IngredientsFragment extends Fragment {

    private static final String ARG_RECIPE_ID = "recipeId";

    private int mColumnCount = 1;
    private int mRecipeId;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public IngredientsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mRecipeId = getArguments().getInt(ARG_RECIPE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredients_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            recyclerView.setAdapter(new IngredientsRecyclerViewAdapter(DummyContent.ITEMS, mRecipeId));
        }
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
