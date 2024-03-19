package bg.softuni._xmlexercise.carDealer.services;

import jakarta.xml.bind.JAXBException;

public interface SaleService {
    void seedSales();

    void getSales() throws JAXBException;
}
