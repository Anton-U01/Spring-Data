package bg.softuni._productshop.services.impl;

import bg.softuni._productshop.data.dtos.ProductDto;
import bg.softuni._productshop.data.dtos.ProductImportDto;
import bg.softuni._productshop.data.dtos.ProductInRangeDto;
import bg.softuni._productshop.data.models.Product;
import bg.softuni._productshop.data.repositories.ProductRepository;
import bg.softuni._productshop.services.ProductService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
