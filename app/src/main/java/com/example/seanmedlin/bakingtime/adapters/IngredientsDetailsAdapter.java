package com.example.seanmedlin.bakingtime.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.seanmedlin.bakingtime.R;
import com.example.seanmedlin.bakingtime.models.Ingredient;

import java.util.ArrayList;


public class IngredientsDetailsAdapter
        extends RecyclerView.Adapter<IngredientsDetailsAdapter.IngredientsDetailsAdapterViewHolder> {

    private ArrayList<Ingredient> mIngredientsData;

    /**
     * Constructor for adapter
     */
    public IngredientsDetailsAdapter() {
    }

    public class IngredientsDetailsAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;

        /**
         * Constructor for the ViewHolder
         */
        IngredientsDetailsAdapterViewHolder(View view) {
            super(view);
            mTextView = view.findViewById(R.id.ingredient_list_item_text_view);
        }
    }

    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView is
     * laid out. Enough ViewHolders will be created to fill the screen and allow for scolling.
     *
     * @param viewGroup The ViewGroup that the ViewHolders are contained within.
     * @param viewType  If your RecyclerView has more than one type of item you can use this
     *                  viewType integer to provide a different layout
     * @return A new IngredientsAdapterViewHolder that holds the View for each list item
     */
    @Override
    public IngredientsDetailsAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                                                  int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForGridItem = R.layout.list_item_ingredient;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForGridItem, viewGroup, shouldAttachToParentImmediately);
        return new IngredientsDetailsAdapterViewHolder(view);
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified position
     *
     * @param ingredientsDetailsAdapterViewHolder The ViewHolder which should be updated to
     *                                            represent the content of the item at the given
     *                                            position in the data set
     * @param position                            The position of the item within the adapter's data set
     */
    @Override
    public void onBindViewHolder(
            IngredientsDetailsAdapterViewHolder ingredientsDetailsAdapterViewHolder, int position) {
        Ingredient ingredient = mIngredientsData.get(position);
        ingredientsDetailsAdapterViewHolder.mTextView.setText(ingredient.getIngredient());
    }

    /**
     * This method simply returns the number of items to display. It is used behind the scenes to
     * help layout our Views and for animations.
     *
     * @return The number of items available in our recipes list.
     */
    @Override
    public int getItemCount() {
        if (mIngredientsData == null) return 0;
        return mIngredientsData.size();
    }

    /**
     * Method that will set new data in the IngredientDetailsAdapter without creating a new one
     *
     * @param ingredientsData The new ingredients data to be displayed
     */
    public void setIngredientsData(ArrayList<Ingredient> ingredientsData) {
        mIngredientsData = ingredientsData;
        notifyDataSetChanged();
    }
}
