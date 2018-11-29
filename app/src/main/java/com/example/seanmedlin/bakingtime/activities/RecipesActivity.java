package com.example.seanmedlin.bakingtime.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.seanmedlin.bakingtime.adapters.RecipesAdapter;
import com.example.seanmedlin.bakingtime.R;
import com.example.seanmedlin.bakingtime.utilities.NetworkUtils;

import java.net.URL;
import java.util.Objects;

public class RecipesActivity extends AppCompatActivity {

    private static final String RECIPES_URL =
            "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    private RecyclerView mRecyclerView;
    private RecipesAdapter mAdapter;
    private GridLayoutManager mLayoutManager;

    private String[] recipesDataSet = {
            "Nutella Pie",
            "Brownies",
            "Yellow Cake",
            "Cheesecake"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        // Set up the RecyclerView
        mRecyclerView = findViewById(R.id.recipes_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // Set up the LayoutManager
        final int columns =
                getResources().getInteger(R.integer.recipes_grid_layout_manager_columns);
        mLayoutManager = new GridLayoutManager(this, columns);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Set up the Adapter
        mAdapter = new RecipesAdapter();
        mAdapter.setRecipesData(recipesDataSet);
        mRecyclerView.setAdapter(mAdapter);

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network.
        NetworkInfo networkInfo = Objects.requireNonNull(connMgr).getActiveNetworkInfo();

        // Make the search query or load error message
        if (networkInfo != null && networkInfo.isConnected()) {
            // Make JSON request to the specified URL
            makeSearchQuery(RECIPES_URL);
            // TODO (3) Finish onCreate
        }
    }

    /**
     * This method constructs the URL (using {@link NetworkUtils}), then fires off an AsyncTask to
     * perform the GET request using our {@link QueryTask}
     *
     * @param urlStr the URL that we will receive the JSON object from
     */
    private void makeSearchQuery(String urlStr) {
        URL searchUrl = NetworkUtils.buildUrl(urlStr);
        new QueryTask().execute(searchUrl);
    }

    private class QueryTask extends AsyncTask<URL, Void, ArrayList<Recipes>> {
    }
}
