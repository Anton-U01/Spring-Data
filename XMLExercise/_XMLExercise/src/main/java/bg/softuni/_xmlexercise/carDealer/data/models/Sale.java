package bg.softuni._xmlexercise.carDealer.data.models;

import jakarta.persistence.*;

@Entity
@Table(name = "sales")
public class Sale extends BaseEntity {
        @ManyToOne
        @JoinColumn(name = "car_id",referencedColumnName = "id")
        private Car car;
        @ManyToOne
        @JoinColumn(name = "customer_id",referencedColumnName = "id")
        private Customer customer;
        private double discount;

        public Sale(Car car, Customer customer, double discount) {
                this.car = car;
                this.customer = customer;
                this.discount = discount;
        }

        public Car getCar() {
                return car;
        }

        public void setCar(Car car) {
                this.car = car;
        }

        public Customer getCustomer() {
                return customer;
        }

        public void setCustomer(Customer customer) {
                this.customer = customer;
        }

        public double getDiscount() {
                return discount;
        }

        public void setDiscount(double discount) {
                this.discount = discount;
        }

        public Sale() {
        }
}