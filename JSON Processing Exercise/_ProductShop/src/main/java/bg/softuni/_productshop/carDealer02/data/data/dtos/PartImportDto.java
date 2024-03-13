package bg.softuni._productshop.carDealer02.data.data.dtos;

import bg.softuni._productshop.carDealer02.data.data.models.Supplier;

import java.math.BigDecimal;

public class PartImportDto {
    private String name;
    private BigDecimal price;
    private int quantity;

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public PartImportDto() {
    }
}
