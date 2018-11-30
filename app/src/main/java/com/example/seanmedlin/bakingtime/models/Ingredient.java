package com.example.seanmedlin.bakingtime.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * A {@link Ingredient} object contains information related to an ingredient
 */
@Entity(tableName = "ingredients_table", foreignKeys = @ForeignKey(entity = Recipe.class,
                                                                   parentColumns = "id",
                                                                   childColumns = "recipe_id"))
public class Ingredient implements Serializable {

    /**
     * ID of the ingredient
     */
    @PrimaryKey
    @ColumnInfo(name = "id")
    private final int mId;

    /**
     * Quantity of ingredient
     */
    @ColumnInfo(name = "quantity")
    private final int mQuantity;

    /**
     * Measure of ingredient
     */
    @ColumnInfo(name = "measure")
    private final String mMeasure;

    /**
     * Name of ingredient
     */
    @ColumnInfo(name = "ingredient")
    private final String mIngredient;

    /**
     * ID of the recipe
     */
    @ColumnInfo(name = "recipe_id")
    private final int mRecipeId;

    /**
     * Constructs a new {@link Ingredient} object
     *
     * @param id ingredient ID
     * @param quantity ingredient quantity
     * @param measure ingredient measurement
     * @param ingredient ingredient name
     * @param recipeId ID of the recipe that the ingredient belongs to
     */
    public Ingredient(int id, int quantity, String measure, String ingredient, int recipeId) {
        mId = id;
        mQuantity = quantity;
        mMeasure = measure;
        mIngredient = ingredient;
        mRecipeId = recipeId;
    }

    /**
     * @return ID of the ingredient
     */
    public int getId() { return mId; }

    /**
     * @return quantity of the ingredient
     */
    public int getQuantity() { return mQuantity; }

    /**
     * @return measure of the ingredient
     */
    public String getMeasure() { return mMeasure; }

    /**
     * @return ingredient name
     */
    public String getIngredient() { return mIngredient; }

    /**
     * @return return ID of the recipe this ingredient belongs to
     */
    public int getRecipeId() { return mRecipeId; }
}
