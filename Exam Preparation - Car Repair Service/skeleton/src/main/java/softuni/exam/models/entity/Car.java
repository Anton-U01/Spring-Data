package softuni.exam.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car extends BaseEntity{
    @Column(name = "car_type",nullable = false)
    @Enumerated(EnumType.STRING)
    private CarType carType;
    @Column(name = "car_make",nullable = false)
    private String carMake;
    @Column(name = "car_model",nullable = false)
    private String carModel;
    @Column(nullable = false)
    private int year;
    @Column(name = "plate_number",nullable = false,unique = true)
    private String plateNumber;
    @Column(nullable = false)
    private int kilometers;
    @Column(nullable = false)
    private double engine;

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public int getKilometers() {
        return kilometers;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }

    public double getEngine() {
        return engine;
    }

    public void setEngine(double engine) {
        this.engine = engine;
    }
}
