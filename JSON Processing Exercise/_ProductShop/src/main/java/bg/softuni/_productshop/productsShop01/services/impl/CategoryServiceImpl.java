package bg.softuni._productshop.productsShop01.services.impl;

import bg.softuni._productshop.productsShop01.data.dtos.CategoryByProductsCountDto;
import bg.softuni._productshop.productsShop01.data.models.Category;
import bg.softuni._productshop.productsShop01.data.models.Product;
import bg.softuni._productshop.productsShop01.data.repositories.CategoryRepository;
import bg.softuni._productshop.productsShop01.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper = new ModelMapper();

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<CategoryByProductsCountDto> getAllCategoriesOrderedByProductsCount() {
        PropertyMap<Category,CategoryByProductsCountDto> propertyMap = new PropertyMap<Category,CategoryByProductsCountDto>() {
            @Override
            protected void configure() {
                map().setCategory(source.getName());

            }
        };
        mapper.addMappings(propertyMap);
        return this.categoryRepository.findAllByOrderByProductsCount()
                .stream()
                .map(c -> {
                    CategoryByProductsCountDto cDto = mapper.map(c,CategoryByProductsCountDto.class);
                    cDto.setProductsCount(c.getProducts().size());
                    BigDecimal sum = c.getProducts().stream().map(Product::getPrice).reduce(BigDecimal::add).get();
                    cDto.setTotalRevenue(sum);
                    double averagePrice = sum.doubleValue() / c.getProducts().size();
                    cDto.setAveragePrice(averagePrice);
                    return cDto;
                })
                .collect(Collectors.toList());

    }
}
