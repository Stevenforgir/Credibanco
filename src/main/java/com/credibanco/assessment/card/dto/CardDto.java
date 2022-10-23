package com.credibanco.assessment.card.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
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
    private char type; //0 Debit card, 1 credit card

    private int phone;

    private boolean created;

    @NotNull(message = "Empty validation number")
    private int validationNumber;

    private boolean activated;
}