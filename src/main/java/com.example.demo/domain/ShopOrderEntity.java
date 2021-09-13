package com.example.demo.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "shop_order")
public class ShopOrderEntity {

    @GeneratedValue
    @Id
    private Long id;

    @Column(name = "hash")
    private String hash;

    @Column(name = "confirmed")
    private Boolean confirmed;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "client_id")
    private ClientEntity clientEntity;

    @Setter(AccessLevel.NONE)
    @OneToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    List<ProductEntity> productList;


/*
  @JoinTable(
  name = "nazwa_tabeli"
  joinColumns = @JoinColumn(name = "klucz naszej encji")
  inverseJoinColumns = @JoinColumn(name = "klucz obcej encji"))
   */
}
