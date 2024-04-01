package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "volcanologists")
@XmlAccessorType(XmlAccessType.FIELD)
public class VolcanologistRootDto {
    @XmlElement(name = "volcanologist")
    private List<VolcanologistSeedDto> volcanologists;

    public List<VolcanologistSeedDto> getVolcanologists() {
        return volcanologists;
    }

    public void setVolcanologists(List<VolcanologistSeedDto> volcanologists) {
        this.volcanologists = volcanologists;
    }
}
