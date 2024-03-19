package bg.softuni._xmlexercise.carDealer.data.dtos.exportDtop;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersSalesDto {
    @XmlElement(name = "customer")
    private List<CustomerSaleExportDto> customers;

    public CustomersSalesDto(List<CustomerSaleExportDto> customers) {
        this.customers = customers;
    }

    public List<CustomerSaleExportDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerSaleExportDto> customers) {
        this.customers = customers;
    }

    public CustomersSalesDto() {
    }
}
