package bg.softuni._xmlexercise.carDealer.data.dtos.exportDtop;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class LocalSupplierDto {
    @XmlElement(name = "supplier")
    private List<LocalSupplierExportDto> suppliers;

    public List<LocalSupplierExportDto> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<LocalSupplierExportDto> suppliers) {
        this.suppliers = suppliers;
    }

    public LocalSupplierDto() {
    }
}
