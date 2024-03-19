package bg.softuni._xmlexercise.carDealer.data.dtos.exportDtop;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartDto {
    @XmlElement(name = "part")
    private List<PartExportDto> parts;

    public PartDto() {
    }

    public List<PartExportDto> getParts() {
        return parts;
    }

    public void setParts(List<PartExportDto> parts) {
        this.parts = parts;
    }
}
