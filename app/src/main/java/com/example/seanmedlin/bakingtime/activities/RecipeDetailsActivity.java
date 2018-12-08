package com.example.seanmedlin.bakingtime.activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.seanmedlin.bakingtime.R;
import com.example.seanmedlin.bakingtime.fragments.IngredientsDetailsFragment;
import com.example.seanmedlin.bakingtime.fragments.StepDetailsFragment;
import com.example.seanmedlin.bakingtime.models.Recipe;

public class RecipeDetailsActivity extends AppCompatActivity {

    private Recipe mRecipe;
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_recipe);

        Intent intent = getIntent();
        mRecipe = (Recipe) intent.getSerializableExtra("recipe");

        if (findViewById(R.id.master_list_frame_layout) != null) {
            mTwoPane = true;

            if(savedInstanceState == null) {
                FragmentManager fragmentManager = getSupportFragmentManager();

                IngredientsDetailsFragment ingredientsDetailsFragment =
                        new IngredientsDetailsFragment();
                fragmentManager.beginTransaction()
                        .add(R.id.master_list_frame_layout, ingredientsDetailsFragment)
                        .commit();
            }
        } else {
            mTwoPane = false;
        }
    }
}
