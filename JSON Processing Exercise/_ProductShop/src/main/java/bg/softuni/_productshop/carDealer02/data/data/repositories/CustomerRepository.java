package bg.softuni._productshop.carDealer02.data.data.repositories;

import bg.softuni._productshop.carDealer02.data.data.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
