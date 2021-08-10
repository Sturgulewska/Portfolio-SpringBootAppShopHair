package com.example.demo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product")
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")

    private String name;

    @Column(name = "netto")
    private Double netto;

    @Column(name = "brutto")
    private Double brutto;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "category_id")
    private Category category;


    public Product(String name, Double netto, Double brutto, Category category) {
        this.name = name;
        this.netto = netto;
        this.brutto = brutto;
        this.category = category;
    }


}
