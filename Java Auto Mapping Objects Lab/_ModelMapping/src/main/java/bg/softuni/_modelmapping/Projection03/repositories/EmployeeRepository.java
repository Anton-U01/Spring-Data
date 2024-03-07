package bg.softuni._modelmapping.Projection03.repositories;

import bg.softuni._modelmapping.Projection03.EmployeeInfoDto;
import bg.softuni._modelmapping.SimpleMapping01.Employee;
import bg.softuni._modelmapping.Projection03.services.EmployeeService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    @Query("SELECT new bg.softuni._modelmapping.Projection03.EmployeeInfoDto(e.firstName,e.lastName,e.birthday,e.salary) FROM Employee e WHERE e.birthday < :date")
    List<EmployeeInfoDto> findAllByBirthdayBefore(LocalDate date);
}
