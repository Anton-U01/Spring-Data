package bg.softuni._productshop.productsShop01.services.impl;

import bg.softuni._productshop.productsShop01.data.dtos.CategoryDto;
import bg.softuni._productshop.productsShop01.data.dtos.ProductImportDto;
import bg.softuni._productshop.productsShop01.data.dtos.UserImportDto;
import bg.softuni._productshop.productsShop01.data.models.Category;
import bg.softuni._productshop.productsShop01.data.models.Product;
import bg.softuni._productshop.productsShop01.data.models.User;
import bg.softuni._productshop.productsShop01.data.repositories.CategoryRepository;
import bg.softuni._productshop.productsShop01.data.repositories.ProductRepository;
import bg.softuni._productshop.productsShop01.data.repositories.UserRepository;
import bg.softuni._productshop.constants.Paths;
import bg.softuni._productshop.productsShop01.services.SeedService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class SeedServiceImpl implements SeedService {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;
    private final Gson gson;
    private final ModelMapper mapper;


    public SeedServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository, ProductRepository productRepository, Gson gson, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.gson = gson;
        this.mapper = mapper;
    }

    @Override
    public void seedUsers() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(Paths.USERS_PATH.toFile());
        UserImportDto[] userImportDto = this.gson.fromJson(fileReader, UserImportDto[].class);
        List<User> users = Arrays.stream(userImportDto)
                .map(dto -> mapper.map(dto, User.class))
                .toList();
        this.userRepository.saveAllAndFlush(users);
    }

    @Override
    public void seedProducts() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(Paths.PRODUCTS_PATH.toFile());
        ProductImportDto[] productImportDtos = this.gson.fromJson(fileReader,ProductImportDto[].class);
        List<Product> products = Arrays.stream(productImportDtos)
                .map(dto -> mapper.map(dto, Product.class))
                .map(this::setSeller)
                .map(this::setBuyer)
                .map(this::setCategory)
                .toList();
        this.productRepository.saveAllAndFlush(products);
    }


    @Override
    public void seedCategories() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(Paths.CATEGORY_PATH.toFile());
        CategoryDto[] categoryDtos = this.gson.fromJson(fileReader,CategoryDto[].class);
        List<Category> categories = Arrays.stream(categoryDtos)
                .map(dto -> mapper.map(dto,Category.class))
                .toList();
        this.categoryRepository.saveAllAndFlush(categories);
    }

    @Override
    public void seedAll() throws FileNotFoundException {
        seedUsers();
        seedCategories();
        seedProducts();
    }

    private Product setBuyer(Product product) {
        if(product.getPrice().compareTo(BigDecimal.valueOf(640)) > 0){
            if(this.userRepository.getRandomUser().isPresent()){
                User buyer = userRepository.getRandomUser().get();
                while (buyer.equals(product.getSeller())){
                    buyer = userRepository.getRandomUser().get();
                }

                product.setBuyer(buyer);
            }
        }

        return product;
    }

    private Product setCategory(Product product) {
        if(categoryRepository.getRandomCategory().isPresent()){
            Category category = categoryRepository.getRandomCategory().get();
            product.setCategories(Set.of(category));
        }
        return product;
    }

    private Product setSeller(Product product) {
        if(categoryRepository.getRandomCategory().isPresent()){
            User user = userRepository.getRandomUser().get();
            product.setSeller(user);
        }
        return product;
    }
}
