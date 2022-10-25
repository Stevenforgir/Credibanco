package com.credibanco.assessment.card.exceptions;

import com.credibanco.assessment.card.dto.CardDtoActivateResponse;
import com.credibanco.assessment.card.dto.CardDtoDeleteResponse;
import com.credibanco.assessment.card.dto.CardDtoResponse;
import com.credibanco.assessment.card.dto.TransactionDtoCreateResponse;

public class HandlerRequestException extends RuntimeException{
    public HandlerRequestException(CardDtoResponse cardDtoResponse, String details) {
        super(String.valueOf(cardDtoResponse) + " {Details: " + details + "}");
    }
    public HandlerRequestException(CardDtoActivateResponse cardDtoResponse, String details) {
        super(String.valueOf(cardDtoResponse) + " {Details: " + details + "}");
    }
    public HandlerRequestException(CardDtoDeleteResponse cardDtoResponse, String details) {
        super(String.valueOf(cardDtoResponse) + " {Details: " + details + "}");
    }
    public HandlerRequestException(TransactionDtoCreateResponse transactionDtoCreateResponse, String details) {
        super(String.valueOf(transactionDtoCreateResponse) + " {Details: " + details + "}");
    }
}
