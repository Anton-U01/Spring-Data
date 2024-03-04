package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo,Long> {
    Set<Shampoo> findAllBySizeOrderById(Size size);
    Set<Shampoo> findAllBySizeOrLabelIdOrderByPriceAsc(Size size,long labelId);
    Set<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price);
    Set<Shampoo> findAllByPriceLessThan(BigDecimal price);
    Set<Shampoo> findAllByIngredientsNameIn(List<String> ingredients);
    @Query("FROM Shampoo s WHERE SIZE(s.ingredients) < :number")
    Set<Shampoo> findAllByIngredientsCount(int number);
}
