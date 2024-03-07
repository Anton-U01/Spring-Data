package bg.softuni._modelmapping.Projection03.services;

import bg.softuni._modelmapping.SimpleMapping01.Employee;
import bg.softuni._modelmapping.Projection03.EmployeeInfoDto;
import bg.softuni._modelmapping.Projection03.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeInfoDto> findInfoForBornBefore(LocalDate date){
        List<EmployeeInfoDto> employeeList = employeeRepository.findAllByBirthdayBefore(date);
        return employeeList;
    }

    @Override
    public void saveEntities(List<Employee> employees) {
        this.employeeRepository.saveAllAndFlush(employees);
    }
}
