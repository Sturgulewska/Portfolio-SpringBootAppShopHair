package com.example.demo.domain;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "order_product")
public class OrderProduct {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "order_id")
    private ShopOrder shopOrder;


}
