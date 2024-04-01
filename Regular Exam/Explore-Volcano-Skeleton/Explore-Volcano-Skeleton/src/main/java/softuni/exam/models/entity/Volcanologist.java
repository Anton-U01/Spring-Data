package softuni.exam.models.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "volcanologists")
public class Volcanologist extends BaseEntity{
    @Column(name = "first_name",unique = true,nullable = false)
    private String firstName;
    @Column(name = "last_name",unique = true,nullable = false)
    private String lastName;
    @Column(nullable = false)
    @Min(18)
    @Max(80)
    private int age;
    @Column(nullable = true)
    private LocalDate exploringFrom;
    @Column(nullable = false)
    @Positive
    private double salary;
    @ManyToOne
    @JoinColumn(name = "exploring_volcano_id",referencedColumnName = "id")
    private Volcano volcano;

    public Volcano getVolcano() {
        return volcano;
    }

    public void setVolcano(Volcano volcano) {
        this.volcano = volcano;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getExploringFrom() {
        return exploringFrom;
    }

    public void setExploringFrom(LocalDate exploringFrom) {
        this.exploringFrom = exploringFrom;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
