package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient,Long> {
    Set<Ingredient> findAllByNameStartingWith(String symbol);
    Set<Ingredient> findAllByNameInOrderByPriceAsc(List<String> listOfIngredients);
    @Query("DELETE Ingredient WHERE name = :name")
    @Transactional
    @Modifying
    void deleteAllByName(String name);

    @Query("UPDATE Ingredient SET price = price * 1.10")
    @Transactional
    @Modifying
    void updateAllIngredientsPrice();

    @Query("UPDATE Ingredient SET price = price * 1.10 WHERE name IN :names")
    @Transactional
    @Modifying
    void updateAllIngredientsPriceWithNamesIn(List<String> names);
}
