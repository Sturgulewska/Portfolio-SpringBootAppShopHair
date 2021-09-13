package com.example.demo.domain.dto;

import lombok.Data;

@Data
public class ProductRowDto {

    // Cena i nazwa poszczególnego produktu
    private String name;
    private Float netto;
    private Float brutto;
}
