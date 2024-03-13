package bg.softuni._productshop.productsShop01.services;

import bg.softuni._productshop.productsShop01.data.dtos.ProductInRangeDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    List<ProductInRangeDto> getProductsWithPriceBetweenAndNoBuyer(BigDecimal price1, BigDecimal price2);
}
