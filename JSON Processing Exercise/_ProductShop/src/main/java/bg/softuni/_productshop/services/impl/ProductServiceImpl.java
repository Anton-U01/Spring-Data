package bg.softuni._productshop.services.impl;

import bg.softuni._productshop.data.repositories.ProductRepository;
import bg.softuni._productshop.services.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
