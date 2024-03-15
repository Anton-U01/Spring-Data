package bg.softuni._xmlexercise.carDealer.data.repositories;


import bg.softuni._xmlexercise.carDealer.data.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {
    @Query("FROM Supplier ORDER BY RAND() LIMIT 1")
    Optional<Supplier> getRandomSupplier();
}
