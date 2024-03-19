package bg.softuni._xmlexercise.carDealer.data.dtos.exportDtop;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class AllCarExportDto {
    @XmlAttribute
    private String make;
    @XmlAttribute
    private String model;
    @XmlAttribute(name = "travelled-distance")
    private long travelledDistance;
    @XmlElement
    private PartDto parts;

    public AllCarExportDto(String make, String model, long travelledDistance, PartDto parts) {
        this.make = make;
        this.model = model;
        this.travelledDistance = travelledDistance;
        this.parts = parts;
    }

    public AllCarExportDto() {
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

    public PartDto getParts() {
        return parts;
    }

    public void setParts(PartDto parts) {
        this.parts = parts;
    }
}
