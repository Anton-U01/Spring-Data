package bg.softuni._productshop.carDealer02.data.data.repositories;

import bg.softuni._productshop.carDealer02.data.data.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {
    @Query("FROM Supplier ORDER BY RAND() LIMIT 1")
    Optional<Supplier> getRandomSupplier();
}
