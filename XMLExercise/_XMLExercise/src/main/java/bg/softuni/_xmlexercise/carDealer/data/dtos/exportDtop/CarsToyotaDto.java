package bg.softuni._xmlexercise.carDealer.data.dtos.exportDtop;

import bg.softuni._xmlexercise.carDealer.data.models.Car;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsToyotaDto {
    @XmlElement(name = "car")
    private List<CarToyotaExportDto> cars;

    public CarsToyotaDto() {
    }

    public List<CarToyotaExportDto> getCars() {
        return cars;
    }

    public void setCars(List<CarToyotaExportDto> cars) {
        this.cars = cars;
    }
}
