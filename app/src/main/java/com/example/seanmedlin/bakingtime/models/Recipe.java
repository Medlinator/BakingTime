package com.example.seanmedlin.bakingtime.models;

import android.arch.persistence.room.Entity;

import java.io.Serializable;

/**
 * A {@link Recipe} object contains information related to a recipe
 */
@Entity(tableName = "recipes_table")
public class Recipe implements Serializable {
    // TODO (1) Create classes for Ingredients and Steps
    // TODO (2) Implement one to many relationship from recipes_table to ingredients_table and steps_table
}
