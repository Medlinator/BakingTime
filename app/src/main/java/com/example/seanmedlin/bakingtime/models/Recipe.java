package com.example.seanmedlin.bakingtime.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * A {@link Recipe} object contains information related to a recipe
 */
@Entity(tableName = "recipes")
public class Recipe implements Serializable {
    // TODO (1) Create classes for Ingredients and Steps
    // TODO (2) Implement one to many relationship from recipes_table to ingredients_table and steps_table

    /**
     * Id of the recipe
     */
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private final int mId;

    /**
     * Name of the recipe
     */
    @NonNull
    @ColumnInfo(name = "name")
    private final String mName;

    /**
     * Number of servings
     */
    @NonNull
    @ColumnInfo(name = "servings")
    private final int mServings;

    /**
     * Recipe image
     */
    @ColumnInfo(name = "image")
    private final String mImage;

    /**
     * Constructs a new {@link Recipe} object
     *
     * @param id recipe id
     * @param name recipe name
     * @param servings number of servings in recipe
     * @param image image for the recipe
     */
    public Recipe(int id, String name, int servings, String image) {
        mId = id;
        mName = name;
        mServings = servings;
        mImage = image;
    }

    /**
     * @return id of the recipe
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
}
