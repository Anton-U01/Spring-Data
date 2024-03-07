package bg.softuni._modelmapping.AdvancedMapping02;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ManagerDto {
    private String firstName;
    private String lastName;
    private List<EmployeeDto> employees;

    public ManagerDto(){
        employees = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<EmployeeDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDto> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        String employeesString = employees.stream().map(e -> " - " + e.toString()).collect(Collectors.joining("\n"));
        return String.format("%s %s | Employees: %d\n%s",getFirstName(),getLastName(),getEmployees().size(),employeesString);
    }

}
