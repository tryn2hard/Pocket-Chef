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
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RecipeDetailFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static RecipeDetailFragment newInstance(int columnCount) {
        RecipeDetailFragment fragment = new RecipeDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
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
            recyclerView.setAdapter(new RecipeDetailRecyclerViewAdapter(DummyContent.ITEMS, this));
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
