package com.example.robot.pocket_chef.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Helper class for providing sample recipeName for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class TestData {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Recipe> ITEMS = new ArrayList<Recipe>();

    private static final int COUNT = 4;

    static {
        // Add some sample items.
        for (int i = 0; i < COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(Recipe item) {
        ITEMS.add(item);

    }

    private static Recipe createDummyItem(int position) {
        switch (position) {
            case 0:
                String recipe1 = "Nutella Pie";
                List<Step> recipe1steps = Arrays.asList(
                        new Step("Starting prep.", "Preheat the oven to 350\u00b0f. Butter a 9\" deep dish pie pan.", "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4", null),
                        new Step("Prep the cookie crust.", "Whisk the graham cracker crumbs, 50 grams of sugar, and 1/2 teaspoon of salt together in a medium bowl. Pour the melted butter and 1 teaspoon of vanilla into the dry ingredients and stir together until evenly mixed.", null, null)
                );

                List<Ingredient> recipe1Ingredients = Arrays.asList(
                        new Ingredient(2, "CUP", "Graham Cracker curmbs"),
                        new Ingredient(6, "TBLSP", "unsalted butter, melted"),
                        new Ingredient(0.5, "CUP", "granulated sugar")
                );
                return new Recipe(String.valueOf(position), recipe1, recipe1steps, recipe1Ingredients);

            case 1:
                String recipe2 = "Brownies";
                List<Step> recipe2Steps = Arrays.asList(
                        new Step("Recipe Introduction", "2. Melt the butter and bittersweet chocolate together in a microwave or a double boiler. If microwaving, heat for 30 seconds at a time, removing bowl and stirring ingredients in between.", "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffdc33_-intro-brownies/-intro-brownies.mp4", null),
                        new Step("Starting prep", "3. Mix both sugars into the melted chocolate in a large mixing bowl until mixture is smooth and uniform.", null, null)

                );

                List<Ingredient> recipe2Ingredients = Arrays.asList(
                        new Ingredient(350, "G", "Bittersweet chocolate (60-70% cacao"),
                        new Ingredient(226, "G", "unsalted butter"),
                        new Ingredient(300, "G", "granulated sugar")
                );
                return new Recipe(String.valueOf(position), recipe2, recipe2Steps, recipe2Ingredients);
            case 2:
                String recipe3 = "Yellow Cake";
                List<Step> recipe3Steps = Arrays.asList(
                        new Step("Starting prep", "1. Preheat the oven to 350\u00b0F. Butter the bottoms and sides of two 9\" round pans with 2\"-high sides. Cover the bottoms of the pans with rounds of parchment paper, and butter the paper as well.", null, null),
                        new Step("Combine dry ingredients.", "2. Combine the cake flour, 400 grams (2 cups) of sugar, baking powder, and 1 teaspoon of salt in the bowl of a stand mixer. Using the paddle attachment, beat at low speed until the dry ingredients are mixed together, about one minute", null, null)
                );

                List<Ingredient> recipe3Ingredients = Arrays.asList(
                        new Ingredient(400, "G", "sifted cake flour"),
                        new Ingredient(700, "G", "granulated sugar"),
                        new Ingredient(4, "TSP", "baking powder")
                );
                return new Recipe(String.valueOf(position), recipe3, recipe3Steps, recipe3Ingredients);
            case 3:
                String recipe4 = "Cheese Cake";
                List<Step> recipe4Steps = Arrays.asList(
                        new Step("Recipe Introduction", "Recipe Introduction", null, null),
                        new Step("Starting prep.", "1. Preheat the oven to 350\u00b0F. Grease the bottom of a 9-inch round springform pan with butter.", null, null)
                );

                List<Ingredient> recipe4Ingredients = Arrays.asList(
                        new Ingredient(2, "CUP", "Graham Cracker crumbs"),
                        new Ingredient(6, "TBLSP", "unsalted butter"),
                        new Ingredient(250, "TSP", "granulated sugar")
                );
                return new Recipe(String.valueOf(position), recipe4, recipe4Steps, recipe4Ingredients);
        }
        return new Recipe(String.valueOf(position), "default", null, null);
    }

    /**
     * A dummy item representing a piece of recipeName.
     */
    public static class Recipe {
        public final String id;
        public final String recipeName;
        public final List<Step> steps;
        public final List<Ingredient> ingredients;


        public Recipe(String id, String recipeName, List<Step> steps, List<Ingredient> ingredients) {
            this.id = id;
            this.recipeName = recipeName;
            this.steps = steps;
            this.ingredients = ingredients;
        }


        @Override
        public String toString() {
            return recipeName;
        }
    }

    public static class Ingredient {
        public final double quantity;
        public final String measure;
        public final String ingredient;

        public Ingredient(double quantity, String measure, String ingredient) {
            this.quantity = quantity;
            this.measure = measure;
            this.ingredient = ingredient;
        }
    }

    public static class Step {

        public final String shortDescription;
        public final String description;
        public final String videoUrl;
        public final String thumbnailUrl;

        public Step(String shortDescription, String description, String videoUrl, String thumbnailUrl) {

            this.shortDescription = shortDescription;
            this.description = description;
            this.videoUrl = videoUrl;
            this.thumbnailUrl = thumbnailUrl;
        }
    }
}


