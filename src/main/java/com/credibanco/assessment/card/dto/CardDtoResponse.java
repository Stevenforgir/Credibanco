package com.credibanco.assessment.card.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  //minimal constructor ( Mandatory Data )
@AllArgsConstructor
@NoArgsConstructor
public class CardDtoResponse {
    private int responseCode;
    private int validationNumber;
    private String resultStatus;
    private String maskedPan;
}
