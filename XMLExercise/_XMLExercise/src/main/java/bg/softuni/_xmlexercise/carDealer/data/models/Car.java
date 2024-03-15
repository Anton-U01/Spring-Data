package bg.softuni._xmlexercise.carDealer.data.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


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
    private long travelledDistance;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "cars_parts",
            joinColumns = @JoinColumn(name = "car_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "part_id",referencedColumnName = "id"))
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

    public long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public Car(String make, String model, long travelledDistance) {
        this.make = make;
        this.model = model;
        this.travelledDistance = travelledDistance;
    }

    public Car() {
    }
}
