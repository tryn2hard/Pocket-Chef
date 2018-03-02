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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.robot.pocket_chef.data.RecipeData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import com.example.robot.pocket_chef.R;
import com.example.robot.pocket_chef.adapters.RecipesRecyclerViewAdapter;
import com.example.robot.pocket_chef.activities.StepDescriptionActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p>
 * interface.
 */
public class RecipesFragment extends Fragment implements
        RecipesRecyclerViewAdapter.RecipesAdapterOnClickHandler {


    private final static String TAG = RecipesFragment.class.getName();

    private int mColumnCount = 2;

    private static final String ENDPOINT =
            "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    private RequestQueue requestQueue;

    private Gson gson;

    private ProgressBar mProgressBar;

    private TextView mEmptyStateTextView;

    private RecyclerView mRecyclerView;

    private RecipesRecyclerViewAdapter mAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RecipesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();

        fetchRecipes();
    }

    private void fetchRecipes() {
        StringRequest request = new StringRequest(Request.Method.GET,
                ENDPOINT,
                onRecipesLoaded,
                onRecipesError);

        requestQueue.add(request);
    }

    private final Response.Listener<String> onRecipesLoaded = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Type recipeListType = new TypeToken<ArrayList<RecipeData.Recipe>>() {
            }.getType();
            RecipeData.RECIPES = gson.fromJson(response, recipeListType);
            Log.i(TAG, RecipeData.RECIPES.size() + " recipes loaded in onResponse");
            mAdapter.setRecipeList(RecipeData.RECIPES);
        }
    };

    private final Response.ErrorListener onRecipesError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e(TAG, error.toString());
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipes_list, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recipe_recycler_view);

        Log.i(TAG, RecipeData.RECIPES.size() + " recipes loaded in onCreateView");

        // Set the adapter

        Context context = view.getContext();

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        } else {
            mColumnCount = 3;
            mRecyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }

        mAdapter = new RecipesRecyclerViewAdapter(RecipeData.RECIPES, this);

        mRecyclerView.setAdapter(mAdapter);

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
