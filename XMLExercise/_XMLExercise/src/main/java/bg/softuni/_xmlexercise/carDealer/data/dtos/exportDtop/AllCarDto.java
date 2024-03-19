package bg.softuni._xmlexercise.carDealer.data.dtos.exportDtop;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class AllCarDto {
    @XmlElement(name = "car")
    private List<AllCarExportDto> cars;

    public List<AllCarExportDto> getCars() {
        return cars;
    }

    public void setCars(List<AllCarExportDto> cars) {
        this.cars = cars;
    }

    public AllCarDto() {
    }
}
