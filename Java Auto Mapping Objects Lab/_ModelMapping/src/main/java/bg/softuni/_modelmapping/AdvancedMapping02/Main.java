package bg.softuni._modelmapping.AdvancedMapping02;

import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        ModelMapper modelMapper = new ModelMapper();
        Employee employee = new Employee("Georgi","Minkov", BigDecimal.valueOf(1500), LocalDate.of(2001,1,30),"Plovdiv",WorkStatus.YES,null,List.of());
        Employee employee1 = new Employee("Ivan","Ignatov", BigDecimal.valueOf(3400), LocalDate.of(2000,9,5),"Sofia",WorkStatus.NO,employee,List.of());
        Employee employee2 = new Employee("Vesko","Petrov", BigDecimal.valueOf(5500), LocalDate.of(1996,11,22),"Burgas",WorkStatus.YES,employee,List.of());
        employee.setEmployees(List.of(employee1,employee2));

        ManagerDto managerDto = modelMapper.map(employee, ManagerDto.class);
        //System.out.println(managerDto);

    }
}
