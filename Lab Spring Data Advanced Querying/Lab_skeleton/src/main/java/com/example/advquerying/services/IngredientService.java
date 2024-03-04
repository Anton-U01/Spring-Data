package com.example.advquerying.services;

import java.util.List;

public interface IngredientService{
    List<String> getAllIngredientsStartingWith(String symbol);
    List<String> getAllIngredientsFromList(List<String> listOfIngredients);
    void deleteIngredientsByName(String name);
    void updateAllIngredientsPrice();
    void updateAllIngredientsPricesWithNamesIn(List<String> names);
}
