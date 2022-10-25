package com.credibanco.assessment.card.exceptions;

import com.credibanco.assessment.card.dto.CardDtoResponse;
import com.credibanco.assessment.card.dto.TransactionDto;
import com.credibanco.assessment.card.dto.TransactionDtoCancelResponse;
import com.credibanco.assessment.card.dto.TransactionDtoCreateResponse;

public class BadRequestException extends RuntimeException{

    public BadRequestException(CardDtoResponse cardDtoResponse, String details) {
        super(cardDtoResponse.toString() + "{Details: " + details + "}");
    }
    public BadRequestException(TransactionDtoCreateResponse transactionDtoCreateResponse, String details) {
        super(transactionDtoCreateResponse.toString() + "{Details: " + details + "}");
    }
    public BadRequestException(TransactionDtoCancelResponse transactionDtoCancelResponse, String details) {
        super(transactionDtoCancelResponse.toString() + "{Details: " + details + "}");
    }
}
