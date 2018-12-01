package com.example.seanmedlin.bakingtime.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.seanmedlin.bakingtime.adapters.RecipesAdapter;
import com.example.seanmedlin.bakingtime.R;
import com.example.seanmedlin.bakingtime.models.Recipe;
import com.example.seanmedlin.bakingtime.utilities.JsonUtils;
import com.example.seanmedlin.bakingtime.utilities.NetworkUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesActivity extends AppCompatActivity
        implements RecipesAdapter.RecipesAdapterOnClickHandler {

    private final String RECIPES_URL =
            "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    private final String BUNDLE_RECYCLER_LAYOUT = "classname.recycler.layout";

    @BindView(R.id.recipes_recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.recipes_error_message_text_view) TextView mErrorMessageTextView;
    @BindView(R.id.recipes_progress_bar) ProgressBar mProgressBar;

    private RecipesAdapter mAdapter;
    private GridLayoutManager mLayoutManager;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        ButterKnife.bind(this);

        // Set up the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // Set up the LayoutManager
        final int COLUMNS =
                getResources().getInteger(R.integer.recipes_grid_layout_manager_columns);
        mLayoutManager = new GridLayoutManager(this, COLUMNS);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Set up the Adapter
        mAdapter = new RecipesAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        // Restore activity data on orientation change
        if (savedInstanceState != null) {
            Parcelable savedRecyclerLayoutState =
                    savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
            mRecyclerView.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
        }

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network.
        NetworkInfo networkInfo = Objects.requireNonNull(connMgr).getActiveNetworkInfo();

        // Query or load error message
        if (networkInfo != null && networkInfo.isConnected()) {
            // Make JSON request to the specified URL
            makeSearchQuery(RECIPES_URL);
        } else {
            mProgressBar.setVisibility(View.INVISIBLE);
            mErrorMessageTextView.setText(R.string.no_connection);
            mErrorMessageTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(BUNDLE_RECYCLER_LAYOUT,
                mRecyclerView.getLayoutManager().onSaveInstanceState());
    }

    /**
     * This method constructs the URL (using {@link NetworkUtils}), then fires off an AsyncTask to
     * perform the GET request using our {@link QueryTask}
     */
    private void makeSearchQuery(String urlStr) {
        URL searchUrl = NetworkUtils.buildUrl(urlStr);
        new QueryTask().execute(searchUrl);
    }

    class QueryTask extends AsyncTask<URL, Void, ArrayList<Recipe>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<Recipe> doInBackground(URL... urls) {
            if (urls.length == 0) {
                return null;
            }

            URL searchUrl = urls[0];
            try {
                String jsonResponse = NetworkUtils
                        .getResponseFromHttpUrl(searchUrl);
                return JsonUtils.getRecipesFromJson(jsonResponse);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Recipe> recipes) {
            mProgressBar.setVisibility(View.INVISIBLE);
            mLayoutManager.smoothScrollToPosition(mRecyclerView, null, 0);

            if (recipes != null) {
                mErrorMessageTextView.setVisibility(View.INVISIBLE);
                mRecyclerView.setVisibility(View.VISIBLE);
                mAdapter.setRecipesData(recipes);
            } else {
                mErrorMessageTextView.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.INVISIBLE);
            }
        }
    }

    /**
     * This method handles RecyclerView item clicks
     *
     * @param recipe the recipe object to be passed on
     */
    @Override
    public void onClick(Recipe recipe) {
        Intent intent = new Intent(this, RecipeDetailsActivity.class);
        intent.putExtra("recipe", recipe);
        startActivity(intent);
    }
}
