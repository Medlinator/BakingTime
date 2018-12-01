package com.example.seanmedlin.bakingtime.models;

import java.io.Serializable;

/**
 * A {@link Ingredient} object contains information related to an ingredient
 */
public class Ingredient implements Serializable {

    /**
     * Quantity of ingredient
     */
    private final int mQuantity;

    /**
     * Measure of ingredient
     */
    private final String mMeasure;

    /**
     * Name of ingredient
     */
    private final String mIngredient;

    /**
     * Constructs a new {@link Ingredient} object
     *
     * @param quantity ingredient quantity
     * @param measure ingredient measurement
     * @param ingredient ingredient name
     */
    public Ingredient(int quantity, String measure, String ingredient) {
        mQuantity = quantity;
        mMeasure = measure;
        mIngredient = ingredient;
    }

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
}
