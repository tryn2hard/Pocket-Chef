package com.example.robot.pocket_chef.dummy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample recipeName for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Recipes> ITEMS = new ArrayList<Recipes>();

    private static final int COUNT = 4;

    static {
        // Add some sample items.
        for (int i = 0; i < COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(Recipes item) {
        ITEMS.add(item);

    }

    private static Recipes createDummyItem(int position) {
        switch (position) {
            case 0:
                String recipe1 = "Nutella Pie";
                List<String> recipe1StepDescription = Arrays.asList(
                        "Starting prep.",
                        "Prep the cookie crust.");
                List<String> recipe1StepInstruction = Arrays.asList(
                        "Preheat the oven to 350\u00b0f. Butter a 9\" deep dish pie pan.",
                        "Whisk the graham cracker crumbs, 50 grams of sugar, and 1/2 teaspoon of salt together in a medium bowl. Pour the melted butter and 1 teaspoon of vanilla into the dry ingredients and stir together until evenly mixed.");
                return new Recipes(String.valueOf(position), recipe1, recipe1StepDescription, recipe1StepInstruction);
            case 1:
                String recipe2 = "Brownies";
                List<String> recipe2StepDescription = Arrays.asList(
                        "Recipe Introduction",
                            "Starting prep");
                List<String> recipe2StepInstruction = Arrays.asList(
                        "2. Melt the butter and bittersweet chocolate together in a microwave or a double boiler. If microwaving, heat for 30 seconds at a time, removing bowl and stirring ingredients in between.",
                        "3. Mix both sugars into the melted chocolate in a large mixing bowl until mixture is smooth and uniform.");
                return new Recipes(String.valueOf(position), recipe2, recipe2StepDescription, recipe2StepInstruction);
            case 2:
                String recipe3 = "Yellow Cake";
                List<String> recipe3StepDescription = Arrays.asList(
                        "Starting prep",
                        "Combine dry ingredients.");
                List<String> recipe3StepInstruction = Arrays.asList(
                        "1. Preheat the oven to 350\u00b0F. Butter the bottoms and sides of two 9\" round pans with 2\"-high sides. Cover the bottoms of the pans with rounds of parchment paper, and butter the paper as well.",
                        "2. Combine the cake flour, 400 grams (2 cups) of sugar, baking powder, and 1 teaspoon of salt in the bowl of a stand mixer. Using the paddle attachment, beat at low speed until the dry ingredients are mixed together, about one minute");
                return new Recipes(String.valueOf(position), recipe3, recipe3StepDescription, recipe3StepInstruction);
            case 3:
                String recipe4 = "Cheese Cake";
                List<String> recipe4StepDescription = Arrays.asList(
                        "Recipe Introduction",
                        "Starting prep.");
                List<String> recipe4StepInstruction = Arrays.asList(
                        "Recipe Introduction",
                        "1. Preheat the oven to 350\u00b0F. Grease the bottom of a 9-inch round springform pan with butter. ");
                return new Recipes(String.valueOf(position), recipe4, recipe4StepDescription, recipe4StepInstruction);
        }
        return new Recipes(String.valueOf(position), "default", null, null);
    }

    /**
     * A dummy item representing a piece of recipeName.
     */
    public static class Recipes {
        public final String id;
        public final String recipeName;
        public final List<String> stepDescription;
        public final List<String> stepInstruction;


        public Recipes(String id, String recipeName, List<String> stepDescription, List<String> stepInstruction) {
            this.id = id;
            this.recipeName = recipeName;
            this.stepDescription = stepDescription;
            this.stepInstruction = stepInstruction;
        }


        @Override
        public String toString() {
            return recipeName;
        }
    }
}


