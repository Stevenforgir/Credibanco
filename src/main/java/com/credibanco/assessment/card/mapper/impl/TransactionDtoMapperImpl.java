package com.credibanco.assessment.card.mapper.impl;

import com.credibanco.assessment.card.dto.TransactionDto;
import com.credibanco.assessment.card.mapper.TransactionDtoMapper;
import com.credibanco.assessment.card.model.Transaction;

public class TransactionDtoMapperImpl implements TransactionDtoMapper {
    @Override
    public TransactionDto toDto(Transaction transaction){
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setPan(transaction.getPan());
        transactionDto.setPurchaseAddress(transaction.getPurchaseAddress());
        transactionDto.setReferenceNumber(transaction.getReferenceNumber());
        transactionDto.setTotalPurchase(transaction.getTotalPurchase());
        return transactionDto;
    }
}
