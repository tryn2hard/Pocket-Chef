package com.example.robot.pocket_chef;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.robot.pocket_chef.dummy.DummyContent.Recipes;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Recipes} and makes a call to the
 * TODO: Replace the implementation with code for your data type.
 */
public class RecipesRecyclerViewAdapter extends RecyclerView.Adapter<RecipesRecyclerViewAdapter.ViewHolder> {

    private final List<Recipes> mValues;

    private final RecipesAdapterOnClickHandler mClickHandler;


    public RecipesRecyclerViewAdapter(List<Recipes> items, RecipesAdapterOnClickHandler clickHandler) {
        mValues = items;
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
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).content);

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final View mView;
        public final TextView mContentView;
        public Recipes mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.recipe_name);
            view.setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            mClickHandler.onClick(adapterPosition);
            Log.d("adapterOnClick", "Is this working");
        }
    }
}
