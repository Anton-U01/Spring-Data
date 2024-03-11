package bg.softuni._productshop;

import bg.softuni._productshop.data.dtos.UsersWithSoldProductsDto;
import bg.softuni._productshop.services.ProductService;
import bg.softuni._productshop.services.SeedService;
import bg.softuni._productshop.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

import static bg.softuni._productshop.constants.Paths.PRODUCTS_IN_RANGE;
import static bg.softuni._productshop.constants.Paths.USERS_SOLD_PRODUCTS;
import static bg.softuni._productshop.constants.Utils.writeJsonToFile;


@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final SeedService seedService;
    private final ProductService productService;
    private final UserService userService;



    public CommandLineRunnerImpl(SeedService seedService, ProductService productService, UserService userService) {
        this.seedService = seedService;
        this.productService = productService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        //this.seedService.seedAll();
        //writeJsonToFile(this.productService.getProductsWithPriceBetweenAndNoBuyer(BigDecimal.valueOf(500),BigDecimal.valueOf(1000)),PRODUCTS_IN_RANGE);

        writeJsonToFile(this.userService.getUsersWithSoldProducts(),USERS_SOLD_PRODUCTS);
    }
}
