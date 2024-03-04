package com.example.advquerying.services;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.ShampooRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShampooServiceImpl implements ShampooService{
    private final ShampooRepository shampooRepository;

    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public List<String> getShampoosBySize(Size size) {
            return this.shampooRepository.findAllBySizeOrderById(size)
                    .stream()
                    .map(shampoo -> String.format("%s %s %.2flv.",
                            shampoo.getBrand(),shampoo.getSize(),shampoo.getPrice()))
                    .toList();
    }

    @Override
    public List<String> getShampoosBySizeOrLabelId(Size size, long id) {
        return this.shampooRepository.findAllBySizeOrLabelIdOrderByPriceAsc(size,id)
                .stream()
                .map(shampoo -> String.format("%s %s %.2flv.",
                        shampoo.getBrand(),shampoo.getSize(),shampoo.getPrice()))
                .toList();
    }

    @Override
    public List<String> getShampoosByPriceGreatherThan(BigDecimal price) {
        return this.shampooRepository.findAllByPriceGreaterThanOrderByPriceDesc(price)
                .stream()
                .map(shampoo -> String.format("%s %s %.2flv.",
                        shampoo.getBrand(),shampoo.getSize(),shampoo.getPrice()))
                .toList();
    }

    @Override
    public int getCountOfShampoosWithLessPriceThan(BigDecimal price) {
        return shampooRepository.findAllByPriceLessThan(price)
                .size();
    }

    @Override
    public List<String> getAllShampoosWithIngredients(List<String> ingredients) {
        return this.shampooRepository.findAllByIngredientsNameIn(ingredients)
                .stream().map(Shampoo::getBrand)
                .toList();
    }

    @Override
    public List<String> getAllShampoosWithIngredientsCountLessThan(int number) {
        return shampooRepository.findAllByIngredientsCount(number)
                .stream().map(Shampoo::getBrand)
                .toList();
    }
}
