package bg.softuni._xmlexercise.carDealer.data.dtos.exportDtop;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class LocalSupplierExportDto {
    @XmlAttribute
    private long id;
    @XmlAttribute
    private String name;
    @XmlAttribute(name = "parts-count")
    private int partsCount;

    public LocalSupplierExportDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPartsCount() {
        return partsCount;
    }

    public void setPartsCount(int partsCount) {
        this.partsCount = partsCount;
    }
}
