package com.example.robot.pocket_chef.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;

import com.example.robot.pocket_chef.R;
import com.example.robot.pocket_chef.data.RecipeData;

/**
 * Implementation of App Widget functionality.
 */
public class WidgetProvider extends AppWidgetProvider {

    public static final String ACTION_DISPLAY_INGREDIENTS =
            "com.example.robot.pocket_chef.widget.ACTION_DISPLAY_INGREDIENTS";
    public static final String EXTRA_STRING_INGREDIENT =
            "com.example.robot.pocket_chef.widget.EXTRA_STRING_INGREDIENT";
    public static final String EXTRA_STRING_MEASURE =
            "com.example.robot.pocket_chef.widget.EXTRA_STRING_MEASURE";
    public static final String EXTRA_STRING_QUANTITY =
            "com.example.robot.pocket_chef.widget.EXTRA_STRING_QUANTITY";
    public final static String RECIPE_ID_ARG = "recipeId";
    private int mRecipeId;

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)){
            mRecipeId = intent.getIntExtra(RECIPE_ID_ARG, -1);
        }
        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager
            appWidgetManager, int[] appWidgetIds) {

        for (int widgetId : appWidgetIds) {

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            if(prefs.contains(RECIPE_ID_ARG)){
                mRecipeId = prefs.getInt(RECIPE_ID_ARG, -1);
            }

            RemoteViews mView = initViews(context, appWidgetManager, R.layout.widget_pocket_chef);
            mView.setEmptyView(R.id.widgetCollectionList, R.id.empty_view);


            if(mRecipeId > -1) {
                mView.setTextViewText(R.id.tv_recipe_title,
                        RecipeData.RECIPES.get(mRecipeId).name);
            }
            appWidgetManager.updateAppWidget(widgetId, mView);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    private RemoteViews initViews(Context context,
                                  AppWidgetManager widgetManager, int widgetId){
        RemoteViews mView = new RemoteViews(context.getPackageName(),
                R.layout.widget_pocket_chef);

        Intent intent = new Intent(context, WidgetListService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
        intent.putExtra(RECIPE_ID_ARG, mRecipeId);

        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        mView.setRemoteAdapter(widgetId, R.id.widgetCollectionList, intent);

        return mView;
    }

}

