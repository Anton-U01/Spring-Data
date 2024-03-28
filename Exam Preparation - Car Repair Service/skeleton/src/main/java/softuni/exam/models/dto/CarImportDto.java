package softuni.exam.models.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarImportDto {
    @XmlElement
    @Size(min = 2,max = 30)
    private String carMake;
    @XmlElement
    @Size(min = 2,max = 30)
    private String carModel;
    @XmlElement
    @Positive
    private int year;
    @XmlElement
    @Size(min = 2,max = 30)
    private String plateNumber;
    @XmlElement
    @Positive
    private int kilometers;
    @XmlElement
    @Min(1)
    private double engine;
    private String carType;

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
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

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        carType = carType;
    }
}
