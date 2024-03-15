package bg.softuni._xmlexercise.constants;

import java.nio.file.Path;

public enum Paths {
    ;
    public static final Path USERS_PATH = Path.of("src","main","resources","files","users.json");
    public static final Path CATEGORY_PATH = Path.of("src","main","resources","files","categories.json");
    public static final Path PRODUCTS_PATH = Path.of("src","main","resources","files","products.json");

    public static final String SUPPLIER_PATH = "src/main/resources/xml/suppliers.xml";
    public static final String CUSTOMER_PATH = "src/main/resources/xml/customers.xml";
    public static final String PARTS_PATH = "src/main/resources/xml/parts.xml";
    public static final String CARS_PATH = "src/main/resources/xml/cars.xml";

    public static final String OUTPUT_ORDERED_CUSTOMERS = "src/main/resources/xml/output/ordered-customers.xml";

}
