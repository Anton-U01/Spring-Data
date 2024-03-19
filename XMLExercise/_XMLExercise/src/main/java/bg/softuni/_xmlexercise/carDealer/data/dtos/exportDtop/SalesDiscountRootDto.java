package bg.softuni._xmlexercise.carDealer.data.dtos.exportDtop;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import javax.print.attribute.standard.MediaSize;
import java.math.BigDecimal;
import java.util.List;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SalesDiscountRootDto {
    @XmlElement(name = "sale")
    private List<SaleDiscountDto> sales;

    public SalesDiscountRootDto() {
    }

    public List<SaleDiscountDto> getSales() {
        return sales;
    }

    public void setSales(List<SaleDiscountDto> sales) {
        this.sales = sales;
    }
}
