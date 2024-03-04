package com.example.advquerying;

import com.example.advquerying.entities.Size;
import com.example.advquerying.services.IngredientService;
import com.example.advquerying.services.IngredientServiceImpl;
import com.example.advquerying.services.ShampooServiceImpl;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {
    private final ShampooServiceImpl shampooService;
    private final IngredientServiceImpl ingredientService;

    public CommandLineRunner(ShampooServiceImpl shampooService, IngredientServiceImpl ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {
        //shampooService.getShampoosBySize(Size.valueOf("MEDIUM")).forEach(System.out::println);
        //shampooService.getShampoosBySizeOrLabelId(Size.valueOf("MEDIUM"),10).forEach(System.out::println);
        //shampooService.getShampoosByPriceGreatherThan(BigDecimal.valueOf(5)).forEach(System.out::println);
        //ingredientService.getAllIngredientsStartingWith("M").forEach(System.out::println);
        //ingredientService.getAllIngredientsFromList(List.of("Lavender","Herbs","Apple")).forEach(System.out::println);
        //System.out.println(shampooService.getCountOfShampoosWithLessPriceThan(BigDecimal.valueOf(8.50)));
        //shampooService.getAllShampoosWithIngredients(List.of("Berry","Mineral-Collagen")).forEach(System.out::println);
        //shampooService.getAllShampoosWithIngredientsCountLessThan(2).forEach(System.out::println);
        //ingredientService.deleteIngredientsByName("Apple");
        //ingredientService.updateAllIngredientsPrice();
        ingredientService.updateAllIngredientsPricesWithNamesIn(List.of("Nettle","Herbs"));
    }
}
