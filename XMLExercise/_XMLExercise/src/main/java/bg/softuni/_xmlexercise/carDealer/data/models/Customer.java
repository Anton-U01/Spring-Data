package bg.softuni._xmlexercise.carDealer.data.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {
    @Column
    @NotNull
    private String name;
    @Column(name = "birth_date")
    private LocalDateTime birthDate;
    @Column(name = "is_young_driver")
    private boolean isYoungDriver;


    @OneToMany(mappedBy = "customer",fetch = FetchType.EAGER)
    private List<Sale> sales;
    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public Customer() {
    }

    public Customer(String name, LocalDateTime birthDate, boolean isYoungDriver) {
        this.name = name;
        this.birthDate = birthDate;
        this.isYoungDriver = isYoungDriver;
    }
}
