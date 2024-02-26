package org.example.entities;

import jakarta.persistence.*;
import org.example.composition.PlateNumber;

import java.math.BigDecimal;

@Entity
@Table (name = "cars")
public class Car extends Vehicle{
    @Basic
    private int seats;

    @OneToOne
    @JoinColumn(name = "plate_number_id",referencedColumnName = "id")
    private PlateNumber plateNumber;

    public Car(){
        super("CAR");
    }

    public Car(String model, BigDecimal price, String fuelType, int seats,PlateNumber number) {
        super("CAR", model, price, fuelType);
        this.seats = seats;
        this.plateNumber = number;
    }
}
