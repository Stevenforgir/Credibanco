package com.credibanco.assessment.card.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  //minimal constructor ( Mandatory Data )
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDtoCancelRequest {
    private long pan;
    private int referenceNumber;
    private int totalPurchase;
}
