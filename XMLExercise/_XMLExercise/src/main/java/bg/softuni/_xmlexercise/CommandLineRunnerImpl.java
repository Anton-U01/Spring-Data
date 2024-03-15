package bg.softuni._xmlexercise;

import bg.softuni._xmlexercise.carDealer.services.*;
import bg.softuni._xmlexercise.carDealer.services.impl.CarServiceImpl;
import bg.softuni._xmlexercise.carDealer.utils.XmlParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileWriter;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;
    private final XmlParser xmlParser;
    private final FileWriter fileWriter;

    public CommandLineRunnerImpl(SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService, XmlParser xmlParser, FileWriter fileWriter) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
        this.xmlParser = xmlParser;
        this.fileWriter = fileWriter;
    }

    @Override
    public void run(String... args) throws Exception {
        //this.supplierService.seedSuppliers();
        //this.partService.seedParts();
        //this.carService.seedCars();
        //this.customerService.seedCustomers();
        //this.saleService.seedSales();
        this.customerService.getAllCustomersOrderedByBirthDate();

    }
}
