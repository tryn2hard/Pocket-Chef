package com.example.robot.pocket_chef.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.robot.pocket_chef.R;
import com.example.robot.pocket_chef.data.TestData;
import com.example.robot.pocket_chef.data.TestData.Recipe;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a ingredients for a recipe
 */
public class IngredientsRecyclerViewAdapter extends
        RecyclerView.Adapter<IngredientsRecyclerViewAdapter.ViewHolder> {

    private final List<Recipe> mRecipes;
    private final int mRecipeId;

    public IngredientsRecyclerViewAdapter(List<Recipe> items, int recipeId) {
        mRecipes = items;
        mRecipeId = recipeId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_ingredients, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mRecipes.get(mRecipeId).ingredients.get(position);
        holder.mQuantity.setText(Double.toString(mRecipes.get(mRecipeId).ingredients.get(position).quantity));
        holder.mMeasure.setText(mRecipes.get(mRecipeId).ingredients.get(position).measure);
        holder.mIngredient.setText(mRecipes.get(mRecipeId).ingredients.get(position).ingredient);

    }

    @Override
    public int getItemCount() {
        return mRecipes.get(mRecipeId).ingredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mQuantity;
        public final TextView mMeasure;
        public final TextView mIngredient;
        public TestData.Ingredient mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mQuantity = (TextView) view.findViewById(R.id.quantity_text_view);
            mMeasure = (TextView) view.findViewById(R.id.measure_text_view);
            mIngredient = (TextView) view.findViewById(R.id.ingredient_text_view);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mMeasure.getText() + "'";
        }
    }
}
