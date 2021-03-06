package com.example.robot.pocket_chef.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.robot.pocket_chef.R;
import com.example.robot.pocket_chef.data.RecipeData.Recipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Recipe} and makes a call to the
 */
public class RecipesRecyclerViewAdapter extends
        RecyclerView.Adapter<RecipesRecyclerViewAdapter.ViewHolder> {

    private final static String TAG = RecipesAdapterOnClickHandler.class.getSimpleName();

    private ArrayList<Recipe> mRecipeList;

    private final RecipesAdapterOnClickHandler mClickHandler;


    public RecipesRecyclerViewAdapter(ArrayList<Recipe> items,
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
        holder.mRecipeName.setText(mRecipeList.get(position).name);
        String imageStringUrl;

        if (mRecipeList.get(position).image.length() > 0) {
            imageStringUrl = mRecipeList.get(position).image;
            Picasso.with(holder.mRecipeImageView.getContext())
                    .load(imageStringUrl).into(holder.mRecipeImageView);
        } else {

            switch (position) {
                case 0:
                    holder.mRecipeImageView.setImageResource(R.drawable.nutella_pie);
                    break;

                case 1:
                    holder.mRecipeImageView.setImageResource(R.drawable.brownies);
                    break;

                case 2:
                    holder.mRecipeImageView.setImageResource(R.drawable.yellow_cake);
                    break;

                case 3:
                    holder.mRecipeImageView.setImageResource(R.drawable.cheesecake);
                    break;

                default:
                    holder.mRecipeImageView.setImageResource(R.mipmap.ic_insert_photo_black_48dp);
            }
        }

    }

    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View mView;
        public final TextView mRecipeName;
        public Recipe mItem;
        public final ImageView mRecipeImageView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mRecipeName = (TextView) view.findViewById(R.id.recipe_name);
            mRecipeImageView = (ImageView) view.findViewById(R.id.recipe_image);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int adapterPosition = getAdapterPosition();
            mClickHandler.onClick(adapterPosition);
            Log.d(TAG, "Adapter position is = " + adapterPosition);

        }
    }

    public void setRecipeList(ArrayList<Recipe> recipeList) {
        mRecipeList = recipeList;
        notifyDataSetChanged();
    }
}
