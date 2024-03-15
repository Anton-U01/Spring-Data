package bg.softuni._xmlexercise.carDealer.data.repositories;

import bg.softuni._xmlexercise.carDealer.data.models.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartRepository extends JpaRepository<Part,Long> {
    @Query("FROM Part ORDER BY RAND() LIMIT 1")
    Optional<Part> getRandomPart();
}
