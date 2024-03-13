package bg.softuni._productshop.carDealer02.data.data.models;

import bg.softuni._productshop.productsShop01.data.models.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "sales")
public class Sale extends BaseEntity {
        @OneToOne
        @JoinColumn(name = "car_id",referencedColumnName = "id")
        private Car car;
        @ManyToOne
        @JoinColumn(name = "customer_id",referencedColumnName = "id")
        private Customer customer;
        private double discount;

}
