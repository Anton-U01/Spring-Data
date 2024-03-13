package bg.softuni._productshop.productsShop01.data.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{
    @Column
    @Size(min = 3,max = 15)
    private String name;


    @ManyToMany(mappedBy = "categories",fetch = FetchType.EAGER)
    private Set<Product> products;

    public Category() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
