package com.example.robot.pocket_chef.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for providing sample name for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class RecipeData {

    /**
     * An array of sample (dummy) items.
     */
    public static ArrayList<Recipe> RECIPES = new ArrayList<Recipe>();

    public void addItem(Recipe item){
        RECIPES.add(item);
    }

    public class Recipe {
        public final int id;
        public final String name;
        public final List<Step> steps;
        public final List<Ingredient> ingredients;
        public final int servings;
        public final String image;


        public Recipe(int id, String name, List<Step> steps, List<Ingredient> ingredients,
                      int servings, String image) {
            this.id = id;
            this.name = name;
            this.steps = steps;
            this.ingredients = ingredients;
            this.servings = servings;
            this.image = image;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public class Ingredient {
        public final double quantity;
        public final String measure;
        public final String ingredient;

        public Ingredient(double quantity, String measure, String ingredient) {
            this.quantity = quantity;
            this.measure = measure;
            this.ingredient = ingredient;
        }
    }

    public class Step {

        public final String shortDescription;
        public final String description;
        public final String videoURL;
        public final String thumbnailURL;
        public final int id;

        public Step(int id, String shortDescription, String description, String videoURL, String thumbnailURL) {

            this.id = id;
            this.shortDescription = shortDescription;
            this.description = description;
            this.videoURL = videoURL;
            this.thumbnailURL = thumbnailURL;
        }
    }
}


