package bg.softuni._productshop.productsShop01.services;

import bg.softuni._productshop.productsShop01.data.dtos.CategoryByProductsCountDto;

import java.util.List;

public interface CategoryService {
    List<CategoryByProductsCountDto> getAllCategoriesOrderedByProductsCount();
}
