package bg.softuni._xmlexercise.carDealer.data.repositories;


import bg.softuni._xmlexercise.carDealer.data.models.Car;
import bg.softuni._xmlexercise.carDealer.data.models.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    @Query("FROM Car ORDER BY RAND() LIMIT 1")
    Optional<Car> getRandomCar();
}
