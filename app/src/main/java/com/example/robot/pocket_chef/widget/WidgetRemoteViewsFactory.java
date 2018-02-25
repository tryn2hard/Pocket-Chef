package com.example.robot.pocket_chef.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
    private List mCollections = new ArrayList<String>();


    public WidgetRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews mView = new RemoteViews(mContext.getPackageName(),
                R.layout.widget_recipe_name_list_view_single);
        mView.setTextViewText(R.id.tv_singleRecipeNameText,
                (String) mCollections.get(position));

        final Intent fillInIntent = new Intent();
        fillInIntent.setAction(WidgetProvider.ACTION_TOAST);
        final Bundle bundle = new Bundle();
        bundle.putString(WidgetProvider.EXTRA_STRING,
                (String) mCollections.get(position));
        fillInIntent.putExtras(bundle);
        mView.setOnClickFillInIntent(R.id.tv_singleRecipeNameText, fillInIntent);

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
            String recipeName = recipe.recipeName;
            mCollections.add(recipeName);
        }
    }
}
