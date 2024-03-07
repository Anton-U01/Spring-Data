package bg.softuni._modelmapping.Projection03;

import bg.softuni._modelmapping.Projection03.repositories.EmployeeRepository;
import bg.softuni._modelmapping.Projection03.services.EmployeeServiceImpl;
import bg.softuni._modelmapping.SimpleMapping01.Employee;
import jakarta.persistence.Column;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Session;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class Main implements CommandLineRunner {
    final EmployeeServiceImpl employeeService;

    public Main(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {
        Employee employee = new Employee("Georgi","Minkov", BigDecimal.valueOf(1500), LocalDate.of(2001,1,30),"Plovdiv");
        Employee employee1 = new Employee("Ivan","Ignatov", BigDecimal.valueOf(3400), LocalDate.of(2000,9,5),"Sofia");
        Employee employee2 = new Employee("Vesko","Petrov", BigDecimal.valueOf(5500), LocalDate.of(1996,11,22),"Burgas");
        List<Employee> employees = new ArrayList<>(List.of(employee,employee1,employee2));

        this.employeeService.saveEntities(employees);

        List<EmployeeInfoDto> list = this.employeeService.findInfoForBornBefore(LocalDate.of(1990, 01, 01));
        System.out.println();
    }
}
