package com.example.seanmedlin.bakingtime.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.seanmedlin.bakingtime.models.Ingredient;
import com.example.seanmedlin.bakingtime.models.Step;

import java.util.ArrayList;

public class IngredientsAdapter
        extends RecyclerView.Adapter<IngredientsAdapter.IngredientsAdapterViewHolder> {

    private ArrayList<Ingredient> mIngredientData;

    public class IngredientsAdapterViewHolder extends RecyclerView.ViewHolder {

        IngredientsAdapterViewHolder(View view) {
            super(view);
        }
    }

    @Override
    public IngredientsAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return null;
    }
}
