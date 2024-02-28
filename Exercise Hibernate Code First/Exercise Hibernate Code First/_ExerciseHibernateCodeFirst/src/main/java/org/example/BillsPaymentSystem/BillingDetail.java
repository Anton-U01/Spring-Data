package org.example.BillsPaymentSystem;

import jakarta.persistence.*;
import org.example.BaseEntity;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BillingDetail{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    @Column
    private int number;
    @ManyToOne
    @JoinColumn(name = "owner_id",referencedColumnName = "id")
    private User owner;
}
