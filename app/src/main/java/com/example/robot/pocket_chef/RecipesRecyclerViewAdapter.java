package com.example.robot.pocket_chef;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.robot.pocket_chef.data.TestData.Recipe;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Recipe} and makes a call to the
 */
public class RecipesRecyclerViewAdapter extends
        RecyclerView.Adapter<RecipesRecyclerViewAdapter.ViewHolder> {

    private final static String TAG = RecipesAdapterOnClickHandler.class.getSimpleName();

    private final List<Recipe> mRecipeList;

    private final RecipesAdapterOnClickHandler mClickHandler;


    public RecipesRecyclerViewAdapter(List<Recipe> items,
                                      RecipesAdapterOnClickHandler clickHandler) {
        mRecipeList = items;
        mClickHandler = clickHandler;

    }

    public interface RecipesAdapterOnClickHandler {
        void onClick(int id);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_recipes, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mRecipeList.get(position);
        holder.mRecipeName.setText(mRecipeList.get(position).recipeName);

    }

    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final View mView;
        public final TextView mRecipeName;
        public Recipe mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mRecipeName = (TextView) view.findViewById(R.id.recipe_name);
            view.setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mRecipeName.getText() + "'";
        }

        @Override
        public void onClick(View v) {

            int adapterPosition = getAdapterPosition();
            mClickHandler.onClick(adapterPosition);
            Log.d(TAG, "Adapter position is = " + adapterPosition);

        }
    }
}
