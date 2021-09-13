package com.example.demo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product")
@NoArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")

    private String name;

    @Column(name = "netto")
    private Float netto;

    @Column(name = "brutto")
    private Float brutto;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;


    public ProductEntity(String name, Float netto, Float brutto, CategoryEntity categoryEntity) {
        this.name = name;
        this.netto = netto;
        this.brutto = brutto;
        this.categoryEntity = categoryEntity;
    }


}
