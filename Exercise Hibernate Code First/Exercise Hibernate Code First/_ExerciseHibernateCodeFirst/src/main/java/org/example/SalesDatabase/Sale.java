package org.example.SalesDatabase;

import jakarta.persistence.*;
import org.example.BaseEntity;

import java.time.LocalDate;

@Entity
@Table(name = "sales")
public class Sale extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "store_location_id",referencedColumnName = "id")
    private StoreLocation storeLocation;
    @Column
    private LocalDate date;
}
