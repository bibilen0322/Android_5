package com.example.kappa.myapplication;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class WidgetDemo extends AppWidgetProvider {
    private static final String STATICACTION ="com.example.myapplication.staticreceiver";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_demo);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
     /*   for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }*/
        Intent clickint = new Intent(context,MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context,0,clickint,0);
        RemoteViews rv = new RemoteViews(context.getPackageName(),R.layout.widget_demo);
        rv.setOnClickPendingIntent(R.id.imageView,pi);
        appWidgetManager.updateAppWidget(appWidgetIds,rv);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context,Intent intent){
        super.onReceive(context,intent);
        RemoteViews rv = new RemoteViews(context.getPackageName(),R.layout.widget_demo);
        Bundle bundle = intent.getExtras();
        if(intent.getAction().equals(STATICACTION)){
            rv.setTextViewText(R.id.appwidget_text,bundle.getString("fruitname"));
            rv.setImageViewResource(R.id.imageView,bundle.getInt("pic"));
            AppWidgetManager appWidgetManagerr = AppWidgetManager.getInstance(context);
            int[] widgetIDs = appWidgetManagerr.getAppWidgetIds(new ComponentName(context,WidgetDemo.class));
            appWidgetManagerr.updateAppWidget(widgetIDs,rv);
        }
    }
}

