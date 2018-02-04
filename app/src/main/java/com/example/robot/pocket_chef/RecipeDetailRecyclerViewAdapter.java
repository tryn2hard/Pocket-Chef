package com.example.robot.pocket_chef;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.robot.pocket_chef.dummy.DummyContent;
import com.example.robot.pocket_chef.dummy.DummyContent.Recipes;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Recipes} and makes a call to the

 * TODO: Replace the implementation with code for your data type.
 */
public class RecipeDetailRecyclerViewAdapter extends
        RecyclerView.Adapter<RecipeDetailRecyclerViewAdapter.ViewHolder> {

    private final List<Recipes> mValues;

    private final DetailAdapterOnClickHandler mClickHandler;


    public RecipeDetailRecyclerViewAdapter(List<DummyContent.Recipes> items,
                                           DetailAdapterOnClickHandler clickHandler) {
        mValues = items;
        mClickHandler = clickHandler;

    }

    public interface DetailAdapterOnClickHandler {
        void onClick(int id);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_recipe_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Recipes mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
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
        }
    }
}
