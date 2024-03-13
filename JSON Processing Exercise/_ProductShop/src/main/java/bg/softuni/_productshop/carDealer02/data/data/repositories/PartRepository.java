package bg.softuni._productshop.carDealer02.data.data.repositories;

import bg.softuni._productshop.carDealer02.data.data.models.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartRepository extends JpaRepository<Part,Long> {
    @Query("FROM Part ORDER BY RAND() LIMIT 1")
    Optional<Part> getRandomPart();
}
