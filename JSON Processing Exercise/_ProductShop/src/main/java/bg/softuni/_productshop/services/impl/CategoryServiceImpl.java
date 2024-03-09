package bg.softuni._productshop.services.impl;

import bg.softuni._productshop.data.repositories.CategoryRepository;
import bg.softuni._productshop.services.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
