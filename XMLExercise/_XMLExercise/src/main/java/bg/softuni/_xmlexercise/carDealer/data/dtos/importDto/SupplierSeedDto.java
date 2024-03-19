package bg.softuni._xmlexercise.carDealer.data.dtos.importDto;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierSeedDto {
    @XmlAttribute
    private String name;
    @XmlAttribute(name = "is-importer")
    private boolean isImporter;

    public SupplierSeedDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImporter() {
        return isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }
}
