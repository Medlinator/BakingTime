package com.example.seanmedlin.bakingtime.widgets;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.seanmedlin.bakingtime.R;

public class RecipeViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private static final String[] items = {
            "Graham Cracker crumbs",
            "unsalted butter, melted",
            "granulated sugar",
            "salt",
            "vanilla",
            "Nutella or other chocolate-hazelnut spread",
            "Mascapone Cheese(room temperature)",
            "heavy cream(cold)",
            "cream cheese(softened)"};

    private Context context = null;
    private int appWidgetId;

    public RecipeViewsFactory(Context context, Intent intent) {
        this.context = context;
        appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public void onCreate() {
        // no-op
    }

    @Override
    public void onDestroy() {
        // no-op
    }

    @Override
    public int getCount() {
        return (items.length);
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews row = new RemoteViews(context.getPackageName(),
                R.layout.list_item_widget);

        row.setTextViewText(android.R.id.text1, items[position]);

        Intent i = new Intent();
        Bundle extras = new Bundle();

        extras.putString("position", items[position]);
        i.putExtras(extras);
        row.setOnClickFillInIntent(android.R.id.text1, i);

        return (row);
    }

    @Override
    public RemoteViews getLoadingView() {
        return (null);
    }

    @Override
    public int getViewTypeCount() {
        return (1);
    }

    @Override
    public long getItemId(int position) {
        return (position);
    }

    @Override
    public boolean hasStableIds() {
        return (true);
    }

    @Override
    public void onDataSetChanged() {
        // no-op
    }
}
