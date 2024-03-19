package bg.softuni._xmlexercise.carDealer.services;

import bg.softuni._xmlexercise.carDealer.data.dtos.exportDtop.CustomerByBirthDateDto;
import bg.softuni._xmlexercise.carDealer.data.models.Customer;
import jakarta.xml.bind.JAXBException;

import java.util.List;

public interface CustomerService {
    void seedCustomers() throws JAXBException;
    void getAllCustomersOrderedByBirthDate() throws JAXBException;

    void getAllCustomersWithBoughtCars() throws JAXBException;
}
