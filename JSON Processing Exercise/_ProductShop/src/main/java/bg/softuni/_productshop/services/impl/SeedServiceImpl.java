package bg.softuni._productshop.services.impl;

import bg.softuni._productshop.data.dtos.UserImportDto;
import bg.softuni._productshop.data.models.User;
import bg.softuni._productshop.data.repositories.CategoryRepository;
import bg.softuni._productshop.data.repositories.ProductRepository;
import bg.softuni._productshop.data.repositories.UserRepository;
import bg.softuni._productshop.services.SeedService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;
    private final Gson gson;
    private final ModelMapper mapper;

    private final Path USERS_PATH = Path.of("src","main","resources","files","users.json");


    public SeedServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository, ProductRepository productRepository, Gson gson, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.gson = gson;
        this.mapper = mapper;
    }

    @Override
    public void seedUsers() throws FileNotFoundException {
        final FileReader fileReader = new FileReader(USERS_PATH.toFile());
        UserImportDto[] userImportDto = this.gson.fromJson(fileReader, UserImportDto[].class);
        List<User> users = Arrays.stream(userImportDto)
                .map(dto -> mapper.map(dto, User.class))
                .toList();
        this.userRepository.saveAllAndFlush(users);
    }

    @Override
    public void seedProducts() {

    }

    @Override
    public void seedCategories() {

    }
}
