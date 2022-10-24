package com.credibanco.assessment.card.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  //minimal constructor ( Mandatory Data )
@AllArgsConstructor
@NoArgsConstructor
public class CardDtoCheckResponse {
    private String maskedPan;
    private String owner;
    private long identification;
    private int phone;
    private boolean status;
}
