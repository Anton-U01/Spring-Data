package bg.softuni._xmlexercise.carDealer.data.dtos.exportDtop;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.lang.reflect.Field;
import java.math.BigDecimal;

@XmlRootElement(name = "part")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartExportDto {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private BigDecimal price;

    public PartExportDto(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public PartExportDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
