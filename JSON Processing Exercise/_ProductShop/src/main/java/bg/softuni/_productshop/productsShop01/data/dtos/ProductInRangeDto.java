package bg.softuni._productshop.productsShop01.data.dtos;

import java.math.BigDecimal;

public class ProductInRangeDto {
    private String name;
    private BigDecimal price;
    private String seller;

    public ProductInRangeDto(String name, BigDecimal price, String fullName) {
        this.name = name;
        this.price = price;
        this.seller = fullName;
    }

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

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public ProductInRangeDto() {
    }
}
