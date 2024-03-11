package bg.softuni._productshop.constants;

import java.nio.file.Path;

public enum Paths {
    ;
    public static final Path USERS_PATH = Path.of("src","main","resources","files","users.json");
    public static final Path CATEGORY_PATH = Path.of("src","main","resources","files","categories.json");
    public static final Path PRODUCTS_PATH = Path.of("src","main","resources","files","products.json");

    public static final Path PRODUCTS_IN_RANGE = Path.of("src","main","resources","outputs","products-in-range.json");
    public static final Path USERS_SOLD_PRODUCTS = Path.of("src","main","resources","outputs","users-sold-products.json");
}
