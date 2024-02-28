package org.example.SalesDatabase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.example.BaseEntity;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    @Column(nullable = false,unique = true)
    private String name;
    @Column
    private int quantity;
    @Column(nullable = false)
    private BigDecimal price;
    @OneToMany(mappedBy = "product")
    private Set<Sale> sales;
}
