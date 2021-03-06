package com.example.demo.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderInfoDto {
    //ilośc produktów
    private Integer productCount;

    //cena rzeczy z koszyka netto
    private Float netto;

    // Cena rzeczy z koszyka brutto
    private Float brutto;

    //Lista produktów
    private List <ProductRowDto> productList;
}
