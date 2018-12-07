package com.example.seanmedlin.bakingtime.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.seanmedlin.bakingtime.R;
import com.example.seanmedlin.bakingtime.activities.IngredientsDetailsActivity;
import com.example.seanmedlin.bakingtime.activities.StepDetailsActivity;
import com.example.seanmedlin.bakingtime.adapters.RecipeDetailsAdapter;
import com.example.seanmedlin.bakingtime.models.Ingredient;
import com.example.seanmedlin.bakingtime.models.Recipe;
import com.example.seanmedlin.bakingtime.models.Step;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailsFragment extends Fragment
        implements RecipeDetailsAdapter.RecipeDetailsAdapterOnClickHandler {

    private final String BUNDLE_RECYCLER_LAYOUT = "classname.recycler.layout";

    @BindView(R.id.fragment_details_recipe_ingredients_linear_layout)
    LinearLayout mLinearLayout;
    @BindView(R.id.fragment_details_recipe_recycler_view)
    RecyclerView mRecyclerView;

    private Recipe mRecipe;
    private ArrayList<Step> mStepsData;
    private ArrayList<Ingredient> mIngredients;
    private RecipeDetailsAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details_recipe, container,
                false);
        ButterKnife.bind(this, rootView);

        Intent intent = getActivity().getIntent();
        mRecipe = (Recipe) intent.getSerializableExtra("recipe");
        mStepsData = mRecipe.getSteps();
        mIngredients = mRecipe.getIngredients();

        // Set Click Listener on ingredients Text View
        mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIngredientsActivity(mIngredients);
            }
        });

        // Set up the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // Set up the LayoutManager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Set up the Adapter
        mAdapter = new RecipeDetailsAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setRecipeData(mStepsData);

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

    /**
     * This method handles RecyclerView item clicks
     *
     * @param step the step object to be passed on
     */
    @Override
    public void onClick(Step step) {
        Intent intent = new Intent(getContext(), StepDetailsActivity.class);
        intent.putExtra("step", step);
        startActivity(intent);
    }

    // TODO Hook up this functionality
    public void startIngredientsActivity(ArrayList<Ingredient> ingredients) {
        Intent intent = new Intent(getContext(), IngredientsDetailsActivity.class);
        intent.putExtra("ingredients", ingredients);
        startActivity(intent);
    }
}
