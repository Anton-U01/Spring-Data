package bg.softuni._xmlexercise.carDealer.data.dtos.importDto;

import bg.softuni._xmlexercise.carDealer.utils.LocalDateTimeAdaptor;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDateTime;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerSeedDto {
    @XmlAttribute
    private String name;
    @XmlElement(name = "birth-date")
    @XmlJavaTypeAdapter(LocalDateTimeAdaptor.class)
    private LocalDateTime birthDate;
    @XmlElement(name = "is-young-driver")
    private boolean isYoungDriver;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public CustomerSeedDto() {
    }
}
