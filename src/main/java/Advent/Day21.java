package Advent;

import java.util.*;

public class Day21 {

    public static void solve(List<String> input) {

        System.out.println("The answer of Day21 part1 is: " + solvePart1(input));
        System.out.println();
        System.out.println("The answer of Day21 part2 is: " + solvePart2(input));
    }

    private static int solvePart1(List<String> input) {
        List<String> allIngredients = getAllIngredients(input);
        List<String> ingredientsWithAllergens = new ArrayList<>(getIngredientsWithAllergens(input).values());
        allIngredients.removeAll(ingredientsWithAllergens);
        return allIngredients.size();
    }

    private static String solvePart2 (List<String> input){
        List<String> ingredients = new ArrayList<>();
        HashMap<String, String> ingredientsWithAllergens = getIngredientsWithAllergens(input);
        List<String> allAllergens = getAllAllergens(input);
        Collections.sort(allAllergens);
        for(String allergen  : allAllergens)
            ingredients.add(ingredientsWithAllergens.get(allergen));
        return String.join(",", ingredients);
    }

    private static List<String> getAllIngredients (List<String> input){
        List<String > allIngredients = new ArrayList<>();
        for (String s : input)
            allIngredients.addAll(Arrays.asList(getIngredients(s).split(" ")));
        return allIngredients;
    }

    private static HashMap<String, String> getIngredientsWithAllergens(List<String> input) {
        HashMap<String, String> ingredientsWithAllergens = new HashMap<>();
        for (String allergen : getAllAllergens(input)) {
            List<String> ingredients = new ArrayList<>();
            for (String s : input)
                if (s.contains(" " + allergen))
                    ingredients.add(getIngredients(s));
            ingredientsWithAllergens.put(allergen, getDoubledIngredients(ingredients));
        }
        return removeDoubles(ingredientsWithAllergens);
    }

    private static HashMap<String, String> removeDoubles(HashMap<String, String> ingredients) {
        HashMap<String, String> ingredientsWithAllergens = new HashMap<>(ingredients);
        while (!isAllergensAssignedToOneIngredients(ingredientsWithAllergens))
            for (String s1 : ingredientsWithAllergens.keySet())
                if (ingredientsWithAllergens.get(s1).split(" ").length == 1)
                    for (String s2 : ingredientsWithAllergens.keySet())
                        if (ingredientsWithAllergens.get(s2).split(" ").length > 1)
                            ingredientsWithAllergens.replace(s2, removeIngredients(ingredientsWithAllergens.get(s1), ingredientsWithAllergens.get(s2)));
        return ingredientsWithAllergens;
    }

    private static String removeIngredients(String ingredient, String ingredients) {
        List<String> removedIngredients = new ArrayList<>();
        for (String s : ingredients.split(" "))
            if (!s.equals(ingredient))
                removedIngredients.add(s);
        return String.join(" ", removedIngredients);
    }

    private static boolean isAllergensAssignedToOneIngredients(HashMap<String, String> allergens) {
        for (String s : allergens.values())
            if (s.split(" ").length > 1)
                return false;
        return true;
    }

    private static String getDoubledIngredients(List<String> ingredients) {
        String doubledIngredients = ingredients.get(0);
        for (String s : ingredients)
            doubledIngredients = getDoubledIngredients(doubledIngredients, s);
        return doubledIngredients;
    }

    private static String getDoubledIngredients(String ingredients1, String ingredients2) {
        List<String> ingredients = new ArrayList<>();
        for (String s1 : ingredients1.split(" "))
            for (String s2 : ingredients2.split(" "))
                if (s1.equals(s2))
                    ingredients.add(s1);
        return String.join(" ", ingredients);
    }

    private static String getIngredients(String ingredientsList) {
        return ingredientsList.substring(0, ingredientsList.indexOf(" ("));
    }

    private static List<String> getAllAllergens(List<String> input) {
        List<String> allAllergens = new ArrayList<>();
        for (String s : input)
            allAllergens.addAll(getAllergens(s));
        return new ArrayList<>(new HashSet<>(allAllergens));
    }

    private static List<String> getAllergens(String ingredientsList) {
        String allergensToString = ingredientsList.substring(ingredientsList.indexOf("(") + 10, ingredientsList.length() - 1);
        return new ArrayList<>(Arrays.asList(allergensToString.split(", ")));
    }
}

