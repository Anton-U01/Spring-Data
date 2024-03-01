package com.example.services;

import com.example.data.entities.Author;
import com.example.data.entities.Category;
import com.example.data.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    private final static String CATEGORY_PATH = "D:\\SOFTUNI\\Spring Data\\Exercise Spring Data Intro\\demo\\src\\main\\java\\com\\example\\files\\categories.txt";
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategories() throws IOException {
        Files.readAllLines(Path.of(CATEGORY_PATH))
                .stream().filter(row -> !row.isEmpty())
                .forEach(row -> {
                    Category category = new Category(row);
                    categoryRepository.saveAndFlush(category);
                });
    }

    @Override
    public Set<Category> getRandomCategories() {
        Set<Category> categories = new HashSet<>();
        int count = ThreadLocalRandom.current().nextInt(1,4);
        for (int i = 0; i < count; i++) {
            int id = ThreadLocalRandom.current().nextInt(1, (int) categoryRepository.count() + 1);
            Category category = categoryRepository.findById(id).get();
            categories.add(category);
        }
        return categories;
    }
}
