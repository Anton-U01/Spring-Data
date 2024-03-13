package bg.softuni._productshop.productsShop01.data.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product extends BaseEntity{
    @Size(min = 3)
    private String name;
    @NotNull
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "buyer_id",referencedColumnName = "id")
    private User buyer;
    @ManyToOne
    @JoinColumn(name = "seller_id",referencedColumnName = "id")
    private User seller;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "products_categories",
    joinColumns = @JoinColumn(name = "product_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "category_id",referencedColumnName = "id"))
    private Set<Category> categories;

    public Product() {
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

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
