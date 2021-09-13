package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "payment")
public class PaymentEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "hash")
    private String hash;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private ShopOrderEntity shopOrderEntity;

    @Column(name = "netto_amount")
    private Float nettoAmount;

    @Column(name = "brutto_amount")
    private Float bruttoAmount;
}
