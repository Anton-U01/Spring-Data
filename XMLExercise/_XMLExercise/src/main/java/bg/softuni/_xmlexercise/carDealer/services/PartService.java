package bg.softuni._xmlexercise.carDealer.services;

import jakarta.xml.bind.JAXBException;

import java.io.FileNotFoundException;

public interface PartService {
    void seedParts() throws FileNotFoundException, JAXBException;
}
