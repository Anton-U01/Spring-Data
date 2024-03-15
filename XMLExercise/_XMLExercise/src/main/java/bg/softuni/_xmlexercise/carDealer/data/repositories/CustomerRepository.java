package bg.softuni._xmlexercise.carDealer.data.repositories;


import bg.softuni._xmlexercise.carDealer.data.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query("FROM Customer ORDER BY RAND() LIMIT 1")
    Optional<Customer> getRandomCustomer();

    Optional<List<Customer>> getAllOrderByBirthDateAndYoungDriver();
}
