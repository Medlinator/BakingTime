package com.example.seanmedlin.bakingtime;

import com.example.seanmedlin.bakingtime.activities.RecipeDetailsActivity;
import com.example.seanmedlin.bakingtime.activities.RecipesActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

@RunWith(AndroidJUnit4.class)
public class RecipesActivityScreenTest {

    public static final String INGREDIENTS_LABEL = "Ingredients";

    @Rule
    public ActivityTestRule<RecipesActivity> mActivityTestRule =
            new ActivityTestRule<>(RecipesActivity.class);

    @Test
    public void clickIngredients_OpensIngredientsDetailsActivity() {
        onData(anything()).inAdapterView(withId(R.id.activity_recipes_recycler_view))
                .atPosition(0).perform(click());

        onView(withId(R.id.fragment_details_recipe_ingredients_label_text_view))
                .check(matches(withText(INGREDIENTS_LABEL)));
    }
}
