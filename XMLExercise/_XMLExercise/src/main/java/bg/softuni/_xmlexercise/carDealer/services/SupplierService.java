package bg.softuni._xmlexercise.carDealer.services;

import jakarta.xml.bind.JAXBException;

import java.io.FileNotFoundException;

public interface SupplierService {
    void seedSuppliers() throws FileNotFoundException, JAXBException;
}
