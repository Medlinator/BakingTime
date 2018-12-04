package com.example.seanmedlin.bakingtime.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.seanmedlin.bakingtime.R;
import com.example.seanmedlin.bakingtime.models.Recipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipesAdapterViewHolder> {

    /**
     * Interface for onClick
     */
    public interface RecipesAdapterOnClickHandler {
        void onClick(Recipe recipe);
    }

    private final RecipesAdapterOnClickHandler mOnClickHandler;
    private ArrayList<Recipe> mRecipeData;

    /**
     * Constructor for adapter
     *
     * @param onClickHandler the onClickHandler for the view item
     */
    public RecipesAdapter(RecipesAdapterOnClickHandler onClickHandler) {
        mOnClickHandler = onClickHandler;
    }

    /**
     * Class declaration for ViewHolder. ViewHolder holds a cache of views that will be used and
     * reused for grid items.
     */
    public class RecipesAdapterViewHolder extends RecyclerView.ViewHolder
            implements OnClickListener {

        private TextView mTextView;
        private ImageView mImageView;

        /**
         * Constructor for our ViewHolder.
         */
        RecipesAdapterViewHolder(View view) {
            super(view);
            mTextView = view.findViewById(R.id.recipe_list_item_text_view);
            mImageView = view.findViewById(R.id.recipe_list_item_image_view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Recipe recipe = mRecipeData.get(adapterPosition);
            mOnClickHandler.onClick(recipe);
        }
    }

    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param viewGroup The ViewGroup that these ViewHolders are contained within.
     * @param viewType  If your RecyclerView has more than one type of item you can use this
     *                  viewType integer to provide a different layout.
     * @return A new RecipesAdapterViewHolder that holds the View for each list item
     */
    @Override
    public RecipesAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForGridItem = R.layout.list_item_recipe;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForGridItem, viewGroup, shouldAttachToParentImmediately);
        return new RecipesAdapterViewHolder(view);
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position.
     *
     * @param recipesAdapterViewHolder The ViewHolder which should be updated to represent the
     *                                 contents of the item at the given position in the data set.
     * @param position                 The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(RecipesAdapterViewHolder recipesAdapterViewHolder, int position) {
        Recipe recipe = mRecipeData.get(position);
        recipesAdapterViewHolder.mTextView.setText(recipe.getName());
        if (!recipe.getImage().isEmpty()) {
            Picasso.get().load(recipe.getImage()).into(recipesAdapterViewHolder.mImageView);
        } else {
            recipesAdapterViewHolder.mImageView.setImageResource(R.drawable.no_image_found);
        }
    }

    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available in our recipes list.
     */
    @Override
    public int getItemCount() {
        if (mRecipeData == null) return 0;
        return mRecipeData.size();
    }

    /**
     * Method that will set new data from the web in the RecipesAdapter without creating a new one.
     *
     * @param recipes The new recipes data to be displayed.
     */
    public void setRecipesData(ArrayList<Recipe> recipes) {
        mRecipeData = recipes;
        notifyDataSetChanged();
    }
}