package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "bikes")
public class Bike extends Vehicle{
    public Bike(){
        super("BIKE");
    }

    public Bike(String model, BigDecimal price, String fuelType) {
        super("BIKE", model, price, fuelType);
    }
}
