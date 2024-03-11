package bg.softuni._productshop.services;

import java.io.FileNotFoundException;

public interface SeedService {
    void seedUsers() throws FileNotFoundException;
    void seedProducts() throws FileNotFoundException;
    void seedCategories() throws FileNotFoundException;

    void seedAll() throws FileNotFoundException;
}
