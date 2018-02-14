package com.example.robot.pocket_chef.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.robot.pocket_chef.R;
import com.example.robot.pocket_chef.adapters.RecipesRecyclerViewAdapter;
import com.example.robot.pocket_chef.activities.StepDescriptionActivity;
import com.example.robot.pocket_chef.data.TestData;

/**
 * A fragment representing a list of Items.
 * <p/>

 * interface.
 */
public class RecipesFragment extends Fragment implements
        RecipesRecyclerViewAdapter.RecipesAdapterOnClickHandler {


    private final static String TAG = RecipesFragment.class.getName();

    private int mColumnCount = 2;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RecipesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipes_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            } else {
                mColumnCount = 3;
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            recyclerView.setAdapter(new RecipesRecyclerViewAdapter(TestData.ITEMS, this));
        }
        return view;
    }

    @Override
    public void onClick(int id) {
        Bundle b = new Bundle();
        b.putInt("recipeId", id);
        final Intent recipeDetailIntent = new Intent(getActivity(), StepDescriptionActivity.class);
        recipeDetailIntent.putExtras(b);
        startActivity(recipeDetailIntent);
        Log.d(TAG, "recipeId is " + id);
    }


}
