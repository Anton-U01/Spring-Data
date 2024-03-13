package bg.softuni._productshop.carDealer02.data.data.dtos;

import java.math.BigDecimal;

public class CarImportDto {
    private String make;
    private String model;
    private BigDecimal travelledDistance;

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

    public CarImportDto() {
    }
}
