package com.credibanco.assessment.card.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data  //minimal constructor ( Mandatory Data )
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {

    @NotNull(message = "Empty PAN")
    private long pan;

    @NotBlank(message = "Empty Owner")
    private String owner;

    @NotNull(message = "Empty identification")
    private long identification;  //cedula

    @NotNull(message = "Empty type")
    private boolean type; //0 Debit card, 1 credit card

    private int phone;

}