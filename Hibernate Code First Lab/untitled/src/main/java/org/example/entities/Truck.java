package org.example.entities;

import jakarta.persistence.*;
import org.example.composition.Driver;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "trucks")
public class Truck extends Vehicle{
    @Column(name = "load_capacity")
    private double loadCapacity;

    @ManyToMany
    @JoinTable(
            name = "trucks_drivers",
            joinColumns = @JoinColumn(name = "truck_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id", referencedColumnName = "id")
    )
    private List<Driver> drivers;

    public Truck(){
        super("TRUCK");
    }

    public Truck(String model, BigDecimal price, String fuelType, double loadCapacity) {
        super("TRUCK", model, price, fuelType);
        this.loadCapacity = loadCapacity;
    }
}
