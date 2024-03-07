package bg.softuni._modelmapping;

import bg.softuni._modelmapping.SimpleMapping01.Employee;
import bg.softuni._modelmapping.SimpleMapping01.EmployeeDto;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class ModelMappingApplication {

    public static void main(String[] args) {

        SpringApplication.run(ModelMappingApplication.class, args);

        Employee employee = new Employee("Georgi","Minkov", BigDecimal.valueOf(1500), LocalDate.of(2001,1,1),"Plovdiv");
        ModelMapper modelMapper = new ModelMapper();
        EmployeeDto employeeDto = modelMapper.map(employee,EmployeeDto.class);

    }
}
