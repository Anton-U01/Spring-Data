package bg.softuni._xmlexercise.carDealer.services;

import jakarta.xml.bind.JAXBException;

import java.io.FileNotFoundException;

public interface CarService {
    void seedCars() throws FileNotFoundException, JAXBException;
}
