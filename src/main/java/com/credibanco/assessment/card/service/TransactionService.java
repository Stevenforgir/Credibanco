package com.credibanco.assessment.card.service;

import com.credibanco.assessment.card.dto.*;

public interface TransactionService {
    TransactionDtoCreateResponse createTransaction(TransactionDto transactionDto);
    TransactionDtoCancelResponse cancelTransaction(TransactionDtoCancelRequest transactionDto);

    TransactionDto findByReferenceNumber(int referenceNumber);
    CardDto findByPan(long pan);
}
