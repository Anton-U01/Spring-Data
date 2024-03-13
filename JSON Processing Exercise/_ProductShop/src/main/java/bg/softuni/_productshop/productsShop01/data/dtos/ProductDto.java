package bg.softuni._productshop.productsShop01.data.dtos;

import java.math.BigDecimal;
import java.util.Set;

public class ProductDto {
    private String name;
    private BigDecimal price;
    private UserDto buyer;

    private UserDto seller;

     private Set<CategoryDto> categories;

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

    public UserDto getBuyer() {
        return buyer;
    }

    public void setBuyer(UserDto buyer) {
        this.buyer = buyer;
    }

    public UserDto getSeller() {
        return seller;
    }

    public void setSeller(UserDto seller) {
        this.seller = seller;
    }

    public Set<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryDto> categories) {
        this.categories = categories;
    }

    public ProductDto() {
    }

    public ProductInRangeDto toProductInRangeDto(){
        return new ProductInRangeDto(name,price,this.seller.getFullName());
    }
}
