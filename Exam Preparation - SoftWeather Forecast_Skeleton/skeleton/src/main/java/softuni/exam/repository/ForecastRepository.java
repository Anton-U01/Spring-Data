package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Forecast;
import softuni.exam.models.entity.dayOfWeek;

import java.util.List;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast,Long> {
        List<Forecast> findAllByCityAndDayOfWeek(City city, dayOfWeek dayOfWeek);

        @Query("SELECT f FROM Forecast f WHERE f.dayOfWeek = 'SUNDAY' AND f.city.population < 150000 ORDER BY f.maxTemperature DESC,f.id ASC")
        List<Forecast> findAllByDayOfWeekAndPopulation();
}
