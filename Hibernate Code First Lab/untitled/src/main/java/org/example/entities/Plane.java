package org.example.entities;

import jakarta.persistence.*;
import org.example.composition.Company;

import java.math.BigDecimal;

@Entity
@Table(name = "planes")
public class Plane extends Vehicle{
    @Column(name = "capacity")
    private int passengerCapacity;

    @ManyToOne
    @JoinColumn(name = "owner_id",referencedColumnName = "id")
    private Company owner;

    public Plane(){
        super("PLANE");
    }

    public Plane(int passengerCapacity){
        super();
        this.passengerCapacity = passengerCapacity;
    }

    public Plane(String model, BigDecimal price, String fuelType, int passengerCapacity) {
        super("PLANE", model, price, fuelType);
        this.passengerCapacity = passengerCapacity;
    }
}
