package bg.softuni._modelmapping.Projection03.services;

import bg.softuni._modelmapping.Projection03.EmployeeInfoDto;
import bg.softuni._modelmapping.SimpleMapping01.Employee;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {
    List<EmployeeInfoDto> findInfoForBornBefore(LocalDate date);
    void saveEntities(List<Employee> employees);
}
