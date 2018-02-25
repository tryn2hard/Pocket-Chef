package com.example.robot.pocket_chef.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.robot.pocket_chef.R;
import com.example.robot.pocket_chef.data.TestData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robot on 2/24/2018.
 */

public class WidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext = null;
    private ArrayList<TestData.Recipe> mCollections = new ArrayList<TestData.Recipe>();


    public WidgetRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews mView = new RemoteViews(mContext.getPackageName(),
                R.layout.widget_recipe_name_list_view_single);
        TestData.Recipe recipe = mCollections.get(position);
        String recipeName = recipe.recipeName;

        mView.setTextViewText(R.id.tv_recipe_name, recipeName);

        String ingredientList = "";

        for(int i = 0; i < recipe.ingredients.size(); i++) {
            double ingredientQuantity = recipe.ingredients.get(i).quantity;
            String ingredientMeasure = recipe.ingredients.get(i).measure;
            String ingredient = recipe.ingredients.get(i).ingredient;
            ingredientList = ingredientList + " " + ingredientQuantity + " " + ingredientMeasure +
                    " " + ingredient + "\n" + "\n";
        }
        mView.setTextViewText(R.id.tv_recipe_ingredients, ingredientList);
        Log.d("WidgetRemoteViewFactory", ingredientList);

        final Intent fillInIntent = new Intent();
        fillInIntent.setAction(WidgetProvider.ACTION_TOAST);
        final Bundle bundle = new Bundle();
        bundle.putString(WidgetProvider.EXTRA_STRING,
                mCollections.get(position).recipeName);
        fillInIntent.putExtras(bundle);
        mView.setOnClickFillInIntent(R.id.tv_recipe_name, fillInIntent);

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
    public void onDestroy() {}

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
        for (int i = 0; i < TestData.ITEMS.size(); i++){
            TestData.Recipe recipe = TestData.ITEMS.get(i);
            mCollections.add(recipe);
        }
    }
}
