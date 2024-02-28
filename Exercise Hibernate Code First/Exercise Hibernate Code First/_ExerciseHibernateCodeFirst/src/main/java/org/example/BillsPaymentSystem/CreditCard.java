package org.example.BillsPaymentSystem;

import jakarta.persistence.*;

@Entity
@Table(name = "credit_cards")
public class CreditCard extends BillingDetail{
    @Column(name = "card_type")
    @Enumerated(EnumType.STRING)
    private CardType cardType;
    @Column(name = "expiration_month")
    private int expirationMonth;
    @Column(name = "expiration_year")
    private int expirationYear;
}
