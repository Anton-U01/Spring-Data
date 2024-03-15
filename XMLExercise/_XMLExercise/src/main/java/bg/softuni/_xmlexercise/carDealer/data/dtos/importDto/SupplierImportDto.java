package bg.softuni._xmlexercise.carDealer.data.dtos.importDto;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierImportDto implements Serializable {

    @XmlElement(name = "supplier")
    private List<SupplierSeedDto> suppliers;


    public SupplierImportDto() {
    }

    public List<SupplierSeedDto> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierSeedDto> suppliers) {
        this.suppliers = suppliers;
    }
}
