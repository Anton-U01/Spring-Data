package com.example.services;

import com.example.data.entities.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {
    void seedCategories() throws IOException;
    Set<Category> getRandomCategories();
}
