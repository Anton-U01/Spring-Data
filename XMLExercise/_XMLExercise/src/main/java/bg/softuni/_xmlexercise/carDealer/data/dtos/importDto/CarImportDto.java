package bg.softuni._xmlexercise.carDealer.data.dtos.importDto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarImportDto {
    @XmlElement(name = "car")
    private List<CarSeedDto> cars;

    public List<CarSeedDto> getCars() {
        return cars;
    }

    public void setCars(List<CarSeedDto> cars) {
        this.cars = cars;
    }

    public CarImportDto() {
    }
}
