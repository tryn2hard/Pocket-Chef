package com.example.robot.pocket_chef.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.robot.pocket_chef.R;
import com.example.robot.pocket_chef.data.RecipeData.Recipe;

import java.util.List;


/**
 * {@link RecyclerView.Adapter} that can display a {@link Recipe}
 */
public class StepDescriptionRecyclerViewAdapter extends
        RecyclerView.Adapter<StepDescriptionRecyclerViewAdapter.ViewHolder> {

    private final List<Recipe> mRecipes;

    private final DetailAdapterOnClickHandler mClickHandler;

    private final int mRecipeId;

    private final static String TAG = StepDescriptionRecyclerViewAdapter.class.getSimpleName();


    public StepDescriptionRecyclerViewAdapter(List<Recipe> items, int RecipeId,
                                              DetailAdapterOnClickHandler clickHandler) {
        mRecipes = items;
        mClickHandler = clickHandler;
        mRecipeId = RecipeId;

    }

    public interface DetailAdapterOnClickHandler {
        void onClick(int id);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_step_description, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mRecipes.get(mRecipeId);
        String id = Integer.toString(mRecipes.get(mRecipeId).steps.get(position).id +1);
        holder.mStepNumberView.setText(id);

        holder.mStepDescriptionView.setText(
                mRecipes.get(mRecipeId).steps.get(position).shortDescription);
    }

    @Override
    public int getItemCount() {
        Recipe selectedRecipe = mRecipes.get(mRecipeId);
        Log.d(TAG, "size = " + selectedRecipe.steps.size());
        return selectedRecipe.steps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final View mView;
        public final TextView mStepNumberView;
        public final TextView mStepDescriptionView;
        public Recipe mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mStepNumberView = (TextView) view.findViewById(R.id.step_number);
            mStepDescriptionView = (TextView) view.findViewById(R.id.step_description);
            view.setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mStepDescriptionView.getText() + "'";
        }

        @Override
        public void onClick(View v) {
            int stepDescriptionPos = getAdapterPosition();
            mClickHandler.onClick(stepDescriptionPos);
        }
    }
}
