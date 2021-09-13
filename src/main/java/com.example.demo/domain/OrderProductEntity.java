package com.example.demo.domain;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "order_product")
public class OrderProductEntity {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "order_id")
    private ShopOrderEntity shopOrderEntity;


}
