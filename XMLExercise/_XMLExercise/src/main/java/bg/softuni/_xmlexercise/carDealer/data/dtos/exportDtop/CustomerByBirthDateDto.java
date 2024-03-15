package bg.softuni._xmlexercise.carDealer.data.dtos.exportDtop;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerByBirthDateDto {
    @XmlElement(name = "customer")
    private List<CustomerByBirthDateExportDto> customers;

    public List<CustomerByBirthDateExportDto> getCustomers() {
        return customers;
    }

    public CustomerByBirthDateDto() {
    }

    public void setCustomers(List<CustomerByBirthDateExportDto> customers) {
        this.customers = customers;
    }

    public CustomerByBirthDateDto(List<CustomerByBirthDateExportDto> customers) {
        this.customers = customers;
    }
}
