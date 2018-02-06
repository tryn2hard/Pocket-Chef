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

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Recipes> ITEM_MAP = new HashMap<String, Recipes>();

    private static final int COUNT = 4;

    static {
        // Add some sample items.
        for (int i = 0; i < COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(Recipes item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static Recipes createDummyItem(int position) {
        switch (position) {
            case 0:
                String recipe1 = "Nutella Pie";
                List<String> recipe1Steps = Arrays.asList(
                        "Preheat the oven to 350\u00b0f. Butter a 9\" deep dish pie pan.",
                        "Whisk the graham cracker crumbs, 50 grams of sugar, and 1/2 " +
                                "teaspoon of salt together in a medium bowl. Pour the " +
                                "melted butter and 1 teaspoon of vanilla into the dry " +
                                "ingredients and stir together until evenly mixed.");
                return new Recipes(String.valueOf(position), recipe1, recipe1Steps);
            case 1:
                String recipe2 = "Brownies";
                List<String> recipe2Steps = Arrays.asList("2. Melt the butter and bittersweet chocolate together in a microwave or a double boiler. If microwaving, heat for 30 seconds at a time, removing bowl and stirring ingredients in between.",
                        "3. Mix both sugars into the melted chocolate in a large mixing bowl until mixture is smooth and uniform.");
                return new Recipes(String.valueOf(position), recipe2, recipe2Steps);
            case 2:
                String recipe3 = "Yellow Cake";
                List<String> recipe3Steps = Arrays.asList("Starting prep", "Combine dry ingredients.");
                return new Recipes(String.valueOf(position), recipe3, recipe3Steps);
            case 3:
                String recipe4 = "Cheese Cake";
                List<String> recipe4Steps = Arrays.asList("Recipe Introduction", "Starting prep.");
                return new Recipes(String.valueOf(position), recipe4, recipe4Steps);
        }
        return new Recipes(String.valueOf(position), "default", null);
    }

    /**
     * A dummy item representing a piece of recipeName.
     */
    public static class Recipes {
        public final String id;
        public final String recipeName;
        public final List<String> steps;


        public Recipes(String id, String recipeName, List<String> steps) {
            this.id = id;
            this.recipeName = recipeName;
            this.steps = steps;
        }


        @Override
        public String toString() {
            return recipeName;
        }
    }
}


