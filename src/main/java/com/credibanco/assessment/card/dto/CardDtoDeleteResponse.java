package com.credibanco.assessment.card.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  //minimal constructor ( Mandatory Data )
@AllArgsConstructor
@NoArgsConstructor
public class CardDtoDeleteResponse {
    private String responseCode;
    private String message;
}
