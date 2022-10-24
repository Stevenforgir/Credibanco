package com.credibanco.assessment.card.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data  //minimal constructor ( Mandatory Data )
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    @NotNull(message = "Empty PAN")
    private long pan;

    @NotNull(message = "Empty reference number")
    private int referenceNumber;

    @NotNull(message = "Empty total purchase")
    private int totalPurchase;

    @NotNull(message = "Empty purchase address")
    private String purchaseAddress;
}
