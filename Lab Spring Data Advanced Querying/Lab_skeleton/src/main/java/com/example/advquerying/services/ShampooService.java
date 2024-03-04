package com.example.advquerying.services;

import com.example.advquerying.entities.Size;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface ShampooService {
    List<String> getShampoosBySize(Size size);
    List<String> getShampoosBySizeOrLabelId(Size size,long id);
    List<String> getShampoosByPriceGreatherThan(BigDecimal price);
    int getCountOfShampoosWithLessPriceThan(BigDecimal price);
    List<String> getAllShampoosWithIngredients(List<String> ingredients);
    List<String> getAllShampoosWithIngredientsCountLessThan(int number);
}
