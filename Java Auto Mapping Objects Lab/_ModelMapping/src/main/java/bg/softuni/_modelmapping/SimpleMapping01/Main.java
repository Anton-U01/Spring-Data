package bg.softuni._modelmapping.SimpleMapping01;

import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Employee employee = new Employee("Georgi","Minkov", BigDecimal.valueOf(1500), LocalDate.of(2001,1,1),"Plovdiv");
        ModelMapper modelMapper = new ModelMapper();
        EmployeeDto employeeDto = modelMapper.map(employee,EmployeeDto.class);
        System.out.println();
    }
}
