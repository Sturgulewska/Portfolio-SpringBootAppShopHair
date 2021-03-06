package com.example.demo.domain.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddProductDto {

    @NotBlank(message = "Nazwa produktu nie może być pusta!")
    private String productName;

    @Min(value = 0, message = "Kwota netto nie może być mniejsza od 0!")
    private Float nettoProduct;

    @Min(value = 0, message = "Kwota brutto nie może być mniejsza od 0!")
    private Float bruttoProduct;

    @NotNull(message = "Należy podać id kategorii!")
    @Min(value = 0, message = "Podana kategoria jest nieporpawna!")
    private Long categoryId;
}
