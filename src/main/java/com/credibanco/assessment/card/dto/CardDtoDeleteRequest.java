package com.credibanco.assessment.card.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data  //minimal constructor ( Mandatory Data )
@AllArgsConstructor
@NoArgsConstructor
public class CardDtoDeleteRequest {
    @NotNull(message = "Empty pan")
    private long pan;
    @NotNull(message = "Empty validation number")
    private int validationNumber;
}
