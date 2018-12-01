package com.example.seanmedlin.bakingtime.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.seanmedlin.bakingtime.R;
import com.example.seanmedlin.bakingtime.adapters.RecipeOverviewAdapter;
import com.example.seanmedlin.bakingtime.models.Ingredient;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientsFragment extends Fragment {

    private final String BUNDLE_RECYCLER_LAYOUT = "classname.recycler.layout";

    @BindView(R.id.recipe_overview_recycler_view)
    RecyclerView mRecyclerView;

    private ArrayList<Ingredient> mIngredients;
    private RecipeOverviewAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ingredients, container, false);
        ButterKnife.bind(this, rootView);
    }
}
