package org.example.entities;


import jakarta.persistence.*;
import org.example.composition.PlateNumber;

import java.lang.reflect.GenericArrayType;
import java.math.BigDecimal;

@Entity
@Table(name = "vehicles")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "our_type")
public abstract class Vehicle {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    @Basic
    private String type;
    @Basic
    private String model;
    @Basic
    private BigDecimal price;
    @Column(name = "fuel_type")
    private String fuelType;

    protected Vehicle() {}

    protected Vehicle(String type) {
        this.type = type;
    }

    public Vehicle(String type, String model, BigDecimal price, String fuelType) {
        this.type = type;
        this.model = model;
        this.price = price;
        this.fuelType = fuelType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}
