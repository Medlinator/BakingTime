package com.example.seanmedlin.bakingtime.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A {@link Recipe} object contains information related to a recipe
 */
public class Recipe implements Serializable {

    /**
     * ID of the recipe
     */
    private final int mId;

    /**
     * Name of the recipe
     */
    private final String mName;

    /**
     * Number of servings
     */
    private final int mServings;

    /**
     * Recipe image
     */
    private final String mImage;

    /**
     * List of ingredients
     */
    private ArrayList<Ingredient> mIngredients;

    /**
     * List of steps
     */
    private ArrayList<Step> mSteps;

    /**
     * Constructs a new {@link Recipe} object
     *
     * @param id recipe ID
     * @param name recipe name
     * @param servings number of servings in recipe
     * @param image image for the recipe
     */
    public Recipe(int id, String name, int servings, String image,
                  ArrayList<Ingredient> ingredients, ArrayList<Step> steps) {
        mId = id;
        mName = name;
        mServings = servings;
        mImage = image;
        mIngredients = ingredients;
        mSteps = steps;
    }

    /**
     * @return ID of the recipe
     */
    public int getId() { return mId; }

    /**
     * @return name of the recipe
     */
    public String getName() { return mName; }

    /**
     * @return number of servings in recipe
     */
    public int getServings() { return mServings; }

    /**
     * @return image of the recipe
     */
    public String getImage() { return mImage; }

    /**
     * @return list of ingredients
     */
    public ArrayList<Ingredient> getIngredients() { return mIngredients; }

    /**
     * @return list of steps
     */
    public ArrayList<Step> getSteps() { return mSteps; }
}
