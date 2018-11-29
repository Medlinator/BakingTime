package com.example.seanmedlin.bakingtime.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.seanmedlin.bakingtime.R;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipesAdapterViewHolder> {

    private String[] mDataset;

    public RecipesAdapter() {}

    /**
     * Class declaration for ViewHolder. ViewHolder holds a cache of views that will be used and
     * reused for grid items.
     */
    public class RecipesAdapterViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;

        /**
         * Constructor for our ViewHolder.
         */
        RecipesAdapterViewHolder(View view) {
            super(view);
            mTextView = view.findViewById(R.id.recipes_text_view);
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
        int layoutIdForGridItem = R.layout.recipe_list_item;
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
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(RecipesAdapterViewHolder recipesAdapterViewHolder, int position) {
        recipesAdapterViewHolder.mTextView.setText(mDataset[position]);

    }

    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available in our recipes list.
     */
    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    /**
     * Method that will set new data from the web in the RecipesAdapter without creating a new one.
     *
     * @param recipesData The new recipes data to be displayed.
     */
    public void setRecipesData(String[] recipesData) {
        mDataset = recipesData;
        notifyDataSetChanged();
    }
}