package bg.softuni._productshop;

import bg.softuni._productshop.carDealer02.data.services.CarService;
import bg.softuni._productshop.carDealer02.data.services.PartService;
import bg.softuni._productshop.carDealer02.data.services.SupplierService;
import bg.softuni._productshop.productsShop01.services.CategoryService;
import bg.softuni._productshop.productsShop01.services.ProductService;
import bg.softuni._productshop.productsShop01.services.SeedService;
import bg.softuni._productshop.productsShop01.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

import static bg.softuni._productshop.constants.Paths.*;
import static bg.softuni._productshop.constants.Utils.writeJsonToFile;


@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final SeedService seedService;
    private final ProductService productService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;



    public CommandLineRunnerImpl(SeedService seedService, ProductService productService, UserService userService, CategoryService categoryService, SupplierService supplierService, PartService partService, CarService carService) {
        this.seedService = seedService;
        this.productService = productService;
        this.userService = userService;
        this.categoryService = categoryService;
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
    }

    @Override
    public void run(String... args) throws Exception {
        //this.seedService.seedAll();
        //writeJsonToFile(this.productService.getProductsWithPriceBetweenAndNoBuyer(BigDecimal.valueOf(500),BigDecimal.valueOf(1000)),PRODUCTS_IN_RANGE);
        //writeJsonToFile(this.userService.getUsersWithSoldProducts(),USERS_SOLD_PRODUCTS);
        //writeJsonToFile(this.categoryService.getAllCategoriesOrderedByProductsCount(), CATEGORIES_ORDERED_BY_PRODUCTS_COUNT);
        //writeJsonToFile(List.of(this.userService.getUsersWithAtLeast1SoldProduct()), USERS_AND_PRODUCTS);
        supplierService.seedSuppliers();
        partService.seedParts();;
        carService.seedCars();
        //carService.getAllCarsParts();
    }
}
