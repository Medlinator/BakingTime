package com.example.seanmedlin.bakingtime.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.seanmedlin.bakingtime.R;
import com.example.seanmedlin.bakingtime.adapters.IngredientsDetailsAdapter;
import com.example.seanmedlin.bakingtime.models.Ingredient;
import com.example.seanmedlin.bakingtime.models.Recipe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientsDetailsFragment extends Fragment {

    private final String BUNDLE_RECYCLER_LAYOUT = "classname.recycler.layout";

    @BindView(R.id.fragment_details_ingredients_recycler_view)
    RecyclerView mRecyclerView;

    private Recipe mRecipe;
    private ArrayList<Ingredient> mIngredientsData;
    private IngredientsDetailsAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details_ingredients, container,
                false);
        ButterKnife.bind(this, rootView);

        Intent intent = getActivity().getIntent();
        if (getResources().getBoolean(R.bool.twoPane)) {
            mRecipe = (Recipe) intent.getSerializableExtra("recipe");
            mIngredientsData = mRecipe.getIngredients();
        } else {
            mIngredientsData = (ArrayList<Ingredient>) intent.getSerializableExtra("ingredients");
        }

        // Set up the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // Set up the LayoutManager
        final int COLUMNS =
                getResources().getInteger(R.integer.recipes_grid_layout_manager_columns);
        mLayoutManager = new GridLayoutManager(getActivity(), COLUMNS);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Set up the Adapter
        mAdapter = new IngredientsDetailsAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setIngredientsData(mIngredientsData);

        // Restore activity data on orientation change
        if (savedInstanceState != null) {
            Parcelable savedRecyclerLayoutState =
                    savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
            mRecyclerView.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
        }

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(BUNDLE_RECYCLER_LAYOUT,
                mRecyclerView.getLayoutManager().onSaveInstanceState());
    }
}
