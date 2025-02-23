package main.Order;

import praktikum.Ingredients;

import java.util.List;

public  class OrderIngrediensData {

    public static Ingredients IngredientsList(List<String> ingridientsList) {
        return new Ingredients(ingridientsList);
    }
}
