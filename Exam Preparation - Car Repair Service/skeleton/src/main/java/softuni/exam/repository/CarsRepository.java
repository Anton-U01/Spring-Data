package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Car;
import softuni.exam.models.entity.CarType;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarsRepository extends JpaRepository<Car,Long>{

    Optional<Car> findByPlateNumber(String plateNumber);
}
