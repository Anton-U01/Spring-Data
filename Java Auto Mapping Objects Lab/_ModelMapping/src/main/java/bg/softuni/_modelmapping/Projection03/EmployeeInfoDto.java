package bg.softuni._modelmapping.Projection03;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeeInfoDto {
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private BigDecimal salary;

    public EmployeeInfoDto(){}

    public EmployeeInfoDto(String firstName, String lastName, LocalDate birthday, BigDecimal salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.salary = salary;
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
