package bg.softuni._productshop.carDealer02.data.services;

import java.io.FileNotFoundException;

public interface CarService {
    void seedCars() throws FileNotFoundException;
    void getAllCarsParts();
}
