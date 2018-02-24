package com.example.robot.pocket_chef.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.example.robot.pocket_chef.R;

/**
 * Implementation of App Widget functionality.
 */
public class PocketChefWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager
            appWidgetManager, int[] appWidgetIds) {


        for (int i = 0; i < appWidgetIds.length; ++i) {

            Intent svcIntent = new Intent(context, IngredientsListService.class);
            svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);
            svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.pocket_chef_widget);
            remoteViews.setRemoteAdapter(R.id.listViewWidget, svcIntent);
            remoteViews.setEmptyView(R.id.listViewWidget, R.id.empty_view);

            appWidgetManager.updateAppWidget(appWidgetIds[i], remoteViews);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

}

