package com.example.seanmedlin.bakingtime.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.seanmedlin.bakingtime.R;
import com.example.seanmedlin.bakingtime.models.Step;

import java.util.ArrayList;

public class RecipeOverviewAdapter
        extends RecyclerView.Adapter<RecipeOverviewAdapter.RecipeOverviewAdapterViewHolder>  {

    /**
     * Interface for onClick
     */
    public interface RecipeOverviewAdapterOnClickHandler {
        void onClick(Step step);
    }

    private final RecipeOverviewAdapterOnClickHandler mOnClickHandler;
    private ArrayList<Step> mStepData;

    /**
     * Constructor for adapter
     *
     * @param onClickHandler the onClickHandler for the view item
     */
    public RecipeOverviewAdapter(RecipeOverviewAdapterOnClickHandler onClickHandler) {
        mOnClickHandler = onClickHandler;
    }

    /**
     * Class declaration for ViewHolder. ViewHolder holds a cache of views that will be used and
     * reused for grid items.
     */
    public class RecipeOverviewAdapterViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        public TextView mTextView;

        /**
         * Constructor for our ViewHolder.
         */
        RecipeOverviewAdapterViewHolder(View view) {
            super(view);
            mTextView = view.findViewById(R.id.step_text_view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Step step = mStepData.get(adapterPosition);
            mOnClickHandler.onClick(step);
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
    public RecipeOverviewAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForGridItem = R.layout.step_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForGridItem, viewGroup, shouldAttachToParentImmediately);
        return new RecipeOverviewAdapterViewHolder(view);
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position.
     *
     * @param recipeOverviewAdapterViewHolder The ViewHolder which should be updated to represent the
     *                                 contents of the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(RecipeOverviewAdapterViewHolder recipeOverviewAdapterViewHolder,
                                 int position) {
        Step step = mStepData.get(position);
        recipeOverviewAdapterViewHolder.mTextView.setText(step.getShortDescription());
    }

    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available in our recipes list.
     */
    @Override
    public int getItemCount() {
        if (mStepData == null) return 0;
        return mStepData.size();
    }

    /**
     * Method that will set new data in the RecipeOverviewAdapter without creating a new one.
     *
     * @param steps The new steps data to be displayed.
     */
    public void setRecipeData(ArrayList<Step> steps) {
        mStepData = steps;
        notifyDataSetChanged();
    }
}
