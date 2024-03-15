package bg.softuni._xmlexercise.carDealer.services.impl;

import bg.softuni._xmlexercise.carDealer.data.models.Car;
import bg.softuni._xmlexercise.carDealer.data.models.Customer;
import bg.softuni._xmlexercise.carDealer.data.models.Sale;
import bg.softuni._xmlexercise.carDealer.data.repositories.CarRepository;
import bg.softuni._xmlexercise.carDealer.data.repositories.CustomerRepository;
import bg.softuni._xmlexercise.carDealer.data.repositories.SaleRepository;
import bg.softuni._xmlexercise.carDealer.services.SaleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;

    private final List<Double> DISCOUNTS = List.of(0.0,0.05,0.10,0.15,0.20,0.30,0.40,0.50);


    public SaleServiceImpl(SaleRepository saleRepository, CustomerRepository customerRepository, CarRepository carRepository) {
        this.saleRepository = saleRepository;
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
    }

    @Override
    public void seedSales() {
        if(saleRepository.count() == 0){
            for(int i = 0; i < 50; i++){
                Sale sale = new Sale();
                sale.setCar(getRandomCar());
                sale.setCustomer(getRandomCustomer());
                sale.setDiscount(DISCOUNTS.get(ThreadLocalRandom.current().nextInt(1, DISCOUNTS.size())));
                saleRepository.saveAndFlush(sale);
            }
        }

    }

    private Customer getRandomCustomer() {
        return this.customerRepository.findById(
                ThreadLocalRandom.current().nextLong(1,customerRepository.count())
        ).get();
    }

    private Car getRandomCar() {
        return this.carRepository.findById(
                ThreadLocalRandom.current().nextLong(1,carRepository.count())
        ).get();
    }
}
