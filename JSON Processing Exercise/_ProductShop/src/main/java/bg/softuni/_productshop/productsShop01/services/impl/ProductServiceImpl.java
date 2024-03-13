package bg.softuni._productshop.productsShop01.services.impl;

import bg.softuni._productshop.productsShop01.data.dtos.ProductDto;
import bg.softuni._productshop.productsShop01.data.dtos.ProductInRangeDto;
import bg.softuni._productshop.productsShop01.data.repositories.ProductRepository;
import bg.softuni._productshop.productsShop01.services.ProductService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper mapper;
    private final Gson gson;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper mapper, Gson gson) {
        this.productRepository = productRepository;
        this.mapper = mapper;
        this.gson = gson;
    }

    @Override
    public List<ProductInRangeDto> getProductsWithPriceBetweenAndNoBuyer(BigDecimal price1, BigDecimal price2) {
        List<ProductInRangeDto> products = this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPrice(price1, price2)
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(p -> mapper.map(p, ProductDto.class))
                .map(ProductDto::toProductInRangeDto)
                .toList();
        return products;
    }
}
