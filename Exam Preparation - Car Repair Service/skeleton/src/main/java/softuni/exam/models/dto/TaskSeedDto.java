package softuni.exam.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import softuni.exam.util.LocalDateTimeAdaptor;

import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@XmlRootElement(name = "task")
@XmlAccessorType(XmlAccessType.FIELD)
public class TaskSeedDto {
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateTimeAdaptor.class)
    private LocalDateTime date;
    @XmlElement
    @Positive
    private BigDecimal price;
    @XmlElement
    private CarIdDto car;
    @XmlElement
    private MechanicFirstNameDto mechanic;
    @XmlElement
    private PartIdDto part;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CarIdDto getCar() {
        return car;
    }

    public void setCar(CarIdDto car) {
        this.car = car;
    }

    public MechanicFirstNameDto getMechanic() {
        return mechanic;
    }

    public void setMechanic(MechanicFirstNameDto mechanic) {
        this.mechanic = mechanic;
    }

    public PartIdDto getPart() {
        return part;
    }

    public void setPart(PartIdDto part) {
        this.part = part;
    }
}
