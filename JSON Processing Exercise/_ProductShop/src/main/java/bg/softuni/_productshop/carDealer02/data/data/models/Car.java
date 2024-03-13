package bg.softuni._productshop.carDealer02.data.data.models;

import bg.softuni._productshop.productsShop01.data.models.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car extends BaseEntity {
    @Column
    @NotNull
    private String make;
    @Column
    private String model;
    @Column(name = "travelled_distance")
    private BigDecimal travelledDistance;

    @ManyToMany(mappedBy = "cars",fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    private List<Part> parts;

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(BigDecimal travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public Car(String make, String model, BigDecimal travelledDistance) {
        this.make = make;
        this.model = model;
        this.travelledDistance = travelledDistance;
    }

    public Car() {
    }
}
