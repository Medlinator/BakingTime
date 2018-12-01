package com.example.seanmedlin.bakingtime.utilities;

import com.example.seanmedlin.bakingtime.models.Ingredient;
import com.example.seanmedlin.bakingtime.models.Recipe;
import com.example.seanmedlin.bakingtime.models.Step;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public final class JsonUtils {

    public static ArrayList<Recipe> getRecipesFromJson(String jsonResponseArrayStr)
            throws JSONException {

        final String RECIPE_ID = "id";
        final String RECIPE_NAME = "name";
        final String RECIPE_SERVINGS = "servings";
        final String RECIPE_IMAGE = "image";
        final String INGREDIENTS_ARRAY = "ingredients";
        final String INGREDIENT_NAME = "ingredient";
        final String INGREDIENT_QUANTITY = "quantity";
        final String INGREDIENT_MEASURE = "measure";
        final String STEPS_ARRAY = "steps";
        final String STEP_ID = "id";
        final String STEP_SHORT_DESCRIPTION = "shortDescription";
        final String STEP_DESCRIPTION = "description";
        final String STEP_VIDEO_URL = "videoURL";
        final String STEP_THUMBNAIL_URL = "thumbnailURL";

        ArrayList<Recipe> recipesArrayList = new ArrayList<>();
        ArrayList<Ingredient> ingredientsArrayList = new ArrayList<>();
        ArrayList<Step> stepsArrayList = new ArrayList<>();

        JSONArray jsonRecipesArray = new JSONArray(jsonResponseArrayStr);

        for (int i = 0; i < jsonRecipesArray.length(); i++) {
            int recipeId;
            String name;
            int servings;
            String image;

            JSONObject recipeObject = jsonRecipesArray.getJSONObject(i);

            // Get recipe ID
            recipeId = recipeObject.getInt(RECIPE_ID);
            // Get recipe name
            name = recipeObject.getString(RECIPE_NAME);
            // Get recipe servings
            servings = recipeObject.getInt(RECIPE_SERVINGS);
            // Get recipe image
            image = recipeObject.getString(RECIPE_IMAGE);

            // Get ingredients for recipe
            JSONArray jsonIngredientsArray = recipeObject.getJSONArray(INGREDIENTS_ARRAY);

            for (int j = 0; j < jsonIngredientsArray.length(); j++) {
                int quantity;
                String measure;
                String ingredient;

                JSONObject ingredientObject = jsonIngredientsArray.getJSONObject(j);

                // Get ingredient quantity
                quantity = ingredientObject.getInt(INGREDIENT_QUANTITY);
                // Get ingredient measure
                measure = ingredientObject.getString(INGREDIENT_MEASURE);
                // Get ingredient name
                ingredient = ingredientObject.getString(INGREDIENT_NAME);

                // Instantiate an ingredient object
                Ingredient currentIngredient = new Ingredient(quantity, measure, ingredient);

                ingredientsArrayList.add(currentIngredient);
            }

            JSONArray jsonStepsArray = recipeObject.getJSONArray(STEPS_ARRAY);

            for (int j = 0; j < jsonStepsArray.length(); j++) {
                int stepId;
                String shortDescription;
                String description;
                String videoUrl;
                String thumbnailUrl;

                JSONObject stepObject = jsonStepsArray.getJSONObject(j);

                // Get step ID
                stepId = stepObject.getInt(STEP_ID);
                // Get short description
                shortDescription = stepObject.getString(STEP_SHORT_DESCRIPTION);
                // Get description
                description = stepObject.getString(STEP_DESCRIPTION);
                // Get video URL
                videoUrl = stepObject.getString(STEP_VIDEO_URL);
                // Get thumbnail URL
                thumbnailUrl = stepObject.getString(STEP_THUMBNAIL_URL);

                // Instantiate a step object
                Step currentStep = new Step(stepId, shortDescription, description, videoUrl,
                        thumbnailUrl, recipeId);

                stepsArrayList.add(currentStep);
            }

            Recipe currentRecipe = new Recipe(recipeId, name, servings, image, ingredientsArrayList,
                    stepsArrayList);

            recipesArrayList.add(currentRecipe);
        }

        return recipesArrayList;
    }
}
