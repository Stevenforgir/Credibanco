package com.credibanco.assessment.card.exceptions;

import com.credibanco.assessment.card.dto.*;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String details){
        super(" {Details: " + details + "}");
    }
    public NotFoundException(CardDtoActivateResponse cardDtoActivateResponse, String details){
        super(String.valueOf(cardDtoActivateResponse) + " {Details: " + details + "}");
    }
    public NotFoundException(CardDtoDeleteResponse cardDtoDeleteResponse, String details){
        super(String.valueOf(cardDtoDeleteResponse) + " {Details: " + details + "}");
    }
    public NotFoundException(TransactionDtoCreateResponse transactionDtoCreateResponse, String details){
        super(String.valueOf(transactionDtoCreateResponse) + " {Details: " + details + "}");
    }
    public NotFoundException(TransactionDtoCancelResponse transactionDtoCancelResponse, String details){
        super(String.valueOf(transactionDtoCancelResponse) + " {Details: " + details + "}");
    }
}
