package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService{
        private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<String> getAllIngredientsStartingWith(String symbol) {
        return ingredientRepository.findAllByNameStartingWith(symbol)
                .stream().map(Ingredient::getName)
                .toList();
    }

    @Override
    public List<String> getAllIngredientsFromList(List<String> listOfIngredients) {
        return ingredientRepository.findAllByNameInOrderByPriceAsc(listOfIngredients)
                .stream().map(Ingredient::getName)
                .toList();
    }

    @Override
    public void deleteIngredientsByName(String name) {
        this.ingredientRepository.deleteAllByName(name);
    }

    @Override
    public void updateAllIngredientsPrice() {
        this.ingredientRepository.updateAllIngredientsPrice();
    }

    @Override
    public void updateAllIngredientsPricesWithNamesIn(List<String> names) {
        this.ingredientRepository.updateAllIngredientsPriceWithNamesIn(names);
    }

}
