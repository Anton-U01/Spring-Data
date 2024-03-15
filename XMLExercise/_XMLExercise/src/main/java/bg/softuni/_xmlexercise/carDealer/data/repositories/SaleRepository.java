package bg.softuni._xmlexercise.carDealer.data.repositories;

import bg.softuni._xmlexercise.carDealer.data.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Long> {
}
