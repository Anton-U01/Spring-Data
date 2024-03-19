package bg.softuni._xmlexercise.carDealer.data.repositories;


import bg.softuni._xmlexercise.carDealer.data.models.Car;
import bg.softuni._xmlexercise.carDealer.data.models.Part;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    @Query("FROM Car ORDER BY RAND() LIMIT 1")
    Optional<Car> getRandomCar();
    Optional<List<Car>> findAllByMakeOrderByModelAscTravelledDistanceDesc(String make);

    Optional<List<Car>> getAllBy();
}
