package bg.softuni._productshop.constants;

import java.nio.file.Path;

public enum Paths {
    ;
    public static final Path USERS_PATH = Path.of("src","main","resources","files","users.json");
    public static final Path CATEGORY_PATH = Path.of("src","main","resources","files","categories.json");
    public static final Path PRODUCTS_PATH = Path.of("src","main","resources","files","products.json");

    public static final Path SUPPLIER_PATH = Path.of("src","main","resources","files","suppliers.json");
    public static final Path CUSTOMER_PATH = Path.of("src","main","resources","files","customers.json");
    public static final Path PARTS_PATH = Path.of("src","main","resources","files","parts.json");
    public static final Path CARS_PATH = Path.of("src","main","resources","files","cars.json");

    public static final Path PRODUCTS_IN_RANGE = Path.of("src","main","resources","outputs","products-in-range.json");
    public static final Path USERS_SOLD_PRODUCTS = Path.of("src","main","resources","outputs","users-sold-products.json");
    public static final Path CATEGORIES_ORDERED_BY_PRODUCTS_COUNT = Path.of("src","main","resources","outputs","categories-by-products-count.json");
    public static final Path USERS_AND_PRODUCTS = Path.of("src","main","resources","outputs","users-and-products.json");

}
