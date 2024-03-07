package bg.softuni._modelmapping.AdvancedMapping02;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Employee {
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private LocalDate birthday;
    private String address;
    private WorkStatus isOnHoliday;
    private Employee manager;
    private List<Employee> employees;

    public Employee(){
        employees = new ArrayList<>();
    }
    public Employee(String firstName, String lastName, BigDecimal salary, LocalDate birthday, String address, WorkStatus isOnHoliday, Employee manager,List<Employee> employees) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.birthday = birthday;
        this.address = address;
        this.isOnHoliday = isOnHoliday;
        this.manager = manager;
        this.employees = employees;
    }

    public WorkStatus getIsOnHoliday() {
        return isOnHoliday;
    }

    public void setIsOnHoliday(WorkStatus isOnHoliday) {
        this.isOnHoliday = isOnHoliday;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employeeSet) {
        this.employees = employeeSet;
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

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
