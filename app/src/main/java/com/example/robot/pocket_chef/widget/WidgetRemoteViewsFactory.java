package com.example.robot.pocket_chef.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.robot.pocket_chef.R;
import com.example.robot.pocket_chef.data.TestData;

import java.util.ArrayList;

/**
 * Created by Robot on 2/24/2018.
 */

public class WidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private final static String RECIPE_ID_ARG = "recipeId";

    private Context mContext = null;
    private ArrayList<TestData.Ingredient> mCollections = new ArrayList<>();
    private int mRecipeId;


    public WidgetRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;
        mRecipeId = intent.getIntExtra(RECIPE_ID_ARG, -1);
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews mView = new RemoteViews(mContext.getPackageName(),
                R.layout.widget_ingredient_list_view_single);

        if(!mCollections.isEmpty()) {
            mView.setTextViewText(R.id.tv_ingredient_quantity,
                    (String) Double.toString(mCollections.get(position).quantity));
            mView.setTextViewText(R.id.tv_ingredient_measure,
                    (String) mCollections.get(position).measure);
            mView.setTextViewText(R.id.tv_ingredient_name,
                    (String) mCollections.get(position).ingredient);

            final Intent fillInIntent = new Intent();
            fillInIntent.setAction(WidgetProvider.ACTION_DISPLAY_INGREDIENTS);
            final Bundle bundle = new Bundle();
            bundle.putString(WidgetProvider.EXTRA_STRING_INGREDIENT,
                    (String) mCollections.get(position).ingredient);
            bundle.putString(WidgetProvider.EXTRA_STRING_QUANTITY,
                    (String) Double.toString(mCollections.get(position).quantity));
            bundle.putString(WidgetProvider.EXTRA_STRING_MEASURE,
                    (String) mCollections.get(position).measure);
            fillInIntent.putExtras(bundle);
        }

        return mView;
    }

    @Override
    public void onCreate() {
        initData();
    }

    @Override
    public void onDataSetChanged() {
        initData();
    }

    @Override
    public void onDestroy() {mCollections.clear();}

    @Override
    public int getCount() {
        return mCollections.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    private void initData(){
        mCollections.clear();
        if(mRecipeId > -1) {
            for (int i = 0; i < TestData.ITEMS.get(mRecipeId).ingredients.size(); i++) {
                TestData.Recipe recipe = TestData.ITEMS.get(mRecipeId);
                TestData.Ingredient ingredient = recipe.ingredients.get(i);
                mCollections.add(ingredient);
            }
        }
    }
}
