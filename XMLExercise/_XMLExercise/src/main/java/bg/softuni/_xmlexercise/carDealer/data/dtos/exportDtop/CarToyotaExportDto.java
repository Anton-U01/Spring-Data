package bg.softuni._xmlexercise.carDealer.data.dtos.exportDtop;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarToyotaExportDto {
    @XmlAttribute
    private long id;
    @XmlAttribute
    private String make;
    @XmlAttribute
    private String model;
    @XmlAttribute(name = "travelled-distance")
    private long travelledDistance;

    public CarToyotaExportDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
